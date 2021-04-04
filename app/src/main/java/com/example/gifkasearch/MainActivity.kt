package com.example.gifkasearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gifkasearch.api.RetrofitBuilder
import com.example.gifkasearch.data.Original
import com.example.gifkasearch.data.ThisData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getGift()
    }

    private fun getGift(){
        recyclerView = findViewById(R.id.recycler_gifts)
        RetrofitBuilder.apiService.getGifts().enqueue(object : Callback<ThisData>{
            override fun onResponse(call: Call<ThisData>, response: Response<ThisData>) {
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity,
                    LinearLayoutManager.VERTICAL,
                    false)
                recyclerView.adapter = response.body()?.data?.map {
                    it.image.original
                }?.let { AdapterGifList(it) }
            }

            override fun onFailure(call: Call<ThisData>, t: Throwable) {
                Log.d("you are stupid","Stupid")
            }

        })
    }

}