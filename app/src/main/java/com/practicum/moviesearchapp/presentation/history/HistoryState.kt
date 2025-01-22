package com.practicum.moviesearchapp.presentation.history

import com.practicum.moviesearchapp.domain.models.Movie

sealed interface HistoryState {

    object Loading: HistoryState

    data class Content(val movies: List<Movie>): HistoryState

    data class Empty(val message: String): HistoryState

}