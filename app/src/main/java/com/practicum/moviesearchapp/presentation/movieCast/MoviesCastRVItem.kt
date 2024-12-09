package com.practicum.moviesearchapp.presentation.movieCast

import com.practicum.moviesearchapp.domain.models.MovieCastPerson

sealed interface MoviesCastRVItem {
    data class HeaderItem(
        val headerText: String,
    ) : MoviesCastRVItem

    data class PersonItem(
        val data: MovieCastPerson,
    ) : MoviesCastRVItem
}