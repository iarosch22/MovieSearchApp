package com.practicum.moviesearchapp.ui.movieCast.models

import com.practicum.moviesearchapp.core.ui.RVItem

sealed interface MovieCastState {
    data class Content(val fullTitle: String, val items: List<RVItem>): MovieCastState

    data class Error(val message: String): MovieCastState
}