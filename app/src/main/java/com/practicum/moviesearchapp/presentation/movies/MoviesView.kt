package com.practicum.moviesearchapp.presentation.movies

import com.practicum.moviesearchapp.ui.movies.models.MoviesState

interface MoviesView {
    fun render(state: MoviesState)

    fun showToast(additionalMessage: String)
}