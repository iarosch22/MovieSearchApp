package com.practicum.moviesearchapp.ui.about.models

import com.practicum.moviesearchapp.domain.models.MovieDetails

sealed interface AboutState {
    data class Content(val movieDetails: MovieDetails): AboutState
    data class Error(val errorMessage: String): AboutState
}