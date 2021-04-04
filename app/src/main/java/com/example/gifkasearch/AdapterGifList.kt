package com.example.gifkasearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gifkasearch.data.Original

class AdapterGifList(private val gifts:List<Original>):RecyclerView.Adapter<AdapterGifList.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {

        val gifka = view.findViewById<ImageView>(R.id.some_gif)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gif_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = gifts[position]
        Glide.with(holder.gifka.context).load(item.url).into(holder.gifka)
    }

    override fun getItemCount(): Int = gifts.size
}