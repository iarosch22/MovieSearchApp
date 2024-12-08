package com.practicum.moviesearchapp.ui.movieCast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practicum.moviesearchapp.R
import com.practicum.moviesearchapp.databinding.ListItemCastBinding
import com.practicum.moviesearchapp.domain.models.MovieCastPerson

class MovieCastViewHolder( private val binding: ListItemCastBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(person: MovieCastPerson) {
        if (person.image != null) {
            binding.actorImage.visibility = View.VISIBLE

            Glide.with(itemView)
                .load(person.image)
                .into(binding.actorImage)
        } else {
            binding.actorImage.visibility = View.GONE
        }

        binding.personName.text = person.name
        binding.personDescription.text = person.description
    }

}