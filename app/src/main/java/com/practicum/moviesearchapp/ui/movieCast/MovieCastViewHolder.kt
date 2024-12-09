package com.practicum.moviesearchapp.ui.movieCast

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practicum.moviesearchapp.databinding.ListItemCastBinding
import com.practicum.moviesearchapp.presentation.movieCast.MoviesCastRVItem

class MovieCastViewHolder( private val binding: ListItemCastBinding ): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MoviesCastRVItem.PersonItem) {
        if (item.data.image == null) {
            binding.actorImage.isVisible = false
        } else {
            Glide.with(itemView)
                .load(item.data.image)
                .into(binding.actorImage)

            binding.actorImage.isVisible = true
        }

        binding.personName.text = item.data.name
        binding.personDescription.text = item.data.description
    }

}