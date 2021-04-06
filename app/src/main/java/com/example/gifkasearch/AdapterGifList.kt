package com.example.gifkasearch

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gifkasearch.data.Original

class AdapterGifList(private val gifts: List<Original>, private val click: (Original) -> Unit) :
    RecyclerView.Adapter<AdapterGifList.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val gifka = view.findViewById<ImageView>(R.id.some_gif)
        val itemOfGif = view.findViewById<View>(R.id.item_holder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.gif_item,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = gifts[position]
        Glide.with(holder.gifka.context).load(item.url).into(holder.gifka)
        holder.itemOfGif.setOnClickListener {
            click(item)
        }
    }

    override fun getItemCount(): Int = gifts.size
}