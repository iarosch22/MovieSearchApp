package com.practicum.moviesearchapp.util

import com.practicum.moviesearchapp.presentation.poster.PosterViewModel
import com.practicum.moviesearchapp.presentation.poster.PosterView

object Creator {
    fun providePosterPresenter(posterView: PosterView, imageUrl: String): PosterViewModel {
        return PosterViewModel(posterView, imageUrl)
    }
}