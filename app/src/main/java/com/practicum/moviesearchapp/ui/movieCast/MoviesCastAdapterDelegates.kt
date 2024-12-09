package com.practicum.moviesearchapp.ui.movieCast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.practicum.moviesearchapp.core.ui.RVItem
import com.practicum.moviesearchapp.databinding.ListItemCastBinding
import com.practicum.moviesearchapp.databinding.ListItemHeaderBinding
import com.practicum.moviesearchapp.presentation.movieCast.MoviesCastRVItem

fun movieClassHeaderDelegate() = adapterDelegateViewBinding<MoviesCastRVItem.HeaderItem, RVItem, ListItemHeaderBinding>(
    { layoutInflater, root ->  ListItemHeaderBinding.inflate(layoutInflater, root, false) }) {

    bind {
        binding.headerTextView.text = item.headerText
    }

}

// Делегат для отображения участников на соответствующем экране
fun movieCastPersonDelegate() = adapterDelegateViewBinding<MoviesCastRVItem.PersonItem, RVItem, ListItemCastBinding>(
    { layoutInflater, root -> ListItemCastBinding.inflate(layoutInflater, root, false) }
) {
    bind {
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