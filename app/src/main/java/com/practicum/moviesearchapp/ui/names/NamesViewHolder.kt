package com.practicum.moviesearchapp.ui.names

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practicum.moviesearchapp.R
import com.practicum.moviesearchapp.domain.models.Person

class NamesViewHolder( parent: ViewGroup ): RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.person_item, parent, false)
) {

    private var cover: ImageView = itemView.findViewById(R.id.cover)
    private var title: TextView = itemView.findViewById(R.id.title)
    private var description: TextView = itemView.findViewById(R.id.description)

    fun bind(person: Person) {
        Glide.with(itemView)
            .load(person.image)
            .placeholder(R.drawable.ic_no_photo)
            .circleCrop()
            .into(cover)

        title.text = person.title
        description.text = person.description
    }

}