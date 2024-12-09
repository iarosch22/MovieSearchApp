package com.practicum.moviesearchapp.ui.movieCast.models

import com.practicum.moviesearchapp.presentation.movieCast.MoviesCastRVItem

sealed interface MovieCastState {
    data class Content(val fullTitle: String, val items: List<MoviesCastRVItem>): MovieCastState

    data class Error(val message: String): MovieCastState
}