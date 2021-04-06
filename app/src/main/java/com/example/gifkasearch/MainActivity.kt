package com.example.gifkasearch

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gifkasearch.api.RetrofitBuilder
import com.example.gifkasearch.data.Original
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var request: EditText
    private lateinit var search: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getGift()
    }

    private fun getGift() {

        recyclerView = findViewById(R.id.recycler_gifts)
        request = findViewById(R.id.search_gifts)
        search = findViewById(R.id.btn_search)
        search.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val gifkis =
                    RetrofitBuilder.apiService.getGifts(request.text.toString())
                withContext(Dispatchers.Main) {
                    with(recyclerView) {
                        layoutManager = GridLayoutManager(
                            this@MainActivity,
                            3,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                        adapter =
                            AdapterGifList(gifkis.data.map { it.image.original }) {
                                openDialog(it)
                            }
                    }
                }
            }
        }
    }

    private fun openDialog(gif: Original) {
        val dialog = Dialog(this)
        with(dialog) {
            setCancelable(false)
            setContentView(R.layout.dialog)
        }

        val sendGifImage = dialog.findViewById<ImageView>(R.id.send_gif)
        val shareButton = dialog.findViewById<Button>(R.id.share)

        val link = gif.url
        val send = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, link)
            type = "text/plan"
        }

        Glide.with(this).load(gif.url).into(sendGifImage)
        val shareActionGif = Intent.createChooser(send, null)

        shareButton.setOnClickListener {
            startActivity(shareActionGif)
            dialog.dismiss()
        }
        dialog.show()

    }

}