package com.practicum.moviesearchapp.ui.movieCast

import androidx.recyclerview.widget.RecyclerView
import com.practicum.moviesearchapp.databinding.ListItemHeaderBinding
import com.practicum.moviesearchapp.presentation.movieCast.MoviesCastRVItem

class MovieCastHeaderViewHolder( private val binding: ListItemHeaderBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MoviesCastRVItem.HeaderItem) {
        binding.headerTextView.text = item.headerText
    }

}