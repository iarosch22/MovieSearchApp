package com.practicum.moviesearchapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(R.layout.movie_item, parent, false)) {

    private val image: ImageView = itemView.findViewById(R.id.ivImage)
    private val title: TextView = itemView.findViewById(R.id.tvTitle)
    private val description: TextView = itemView.findViewById(R.id.tvDescription)

    fun bind(item: Movie) {
        Glide.with(itemView)
            .load(item.image)
            .into(image)

        title.text = item.title
        description.text = item.description
    }

}