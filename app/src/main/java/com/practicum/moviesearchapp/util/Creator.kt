package com.practicum.moviesearchapp.util

import com.practicum.moviesearchapp.presentation.poster.PosterPresenter
import com.practicum.moviesearchapp.presentation.poster.PosterView

object Creator {
    fun providePosterPresenter(posterView: PosterView, imageUrl: String): PosterPresenter {
        return PosterPresenter(posterView, imageUrl)
    }
}