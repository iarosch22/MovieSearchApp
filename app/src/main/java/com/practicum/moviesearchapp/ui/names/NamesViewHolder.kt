package com.practicum.moviesearchapp.ui.names

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practicum.moviesearchapp.R
import com.practicum.moviesearchapp.domain.models.Name

class NamesViewHolder( parent: ViewGroup ): RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.name_item, parent, false)
) {

    private var cover: ImageView = itemView.findViewById(R.id.cover)
    private var title: TextView = itemView.findViewById(R.id.title)
    private var description: TextView = itemView.findViewById(R.id.description)

    fun bind(name: Name) {
        Glide.with(itemView)
            .load(name.image)
            .placeholder(R.drawable.ic_no_photo)
            .circleCrop()
            .into(cover)

        title.text = name.title
        description.text = name.description
    }

}