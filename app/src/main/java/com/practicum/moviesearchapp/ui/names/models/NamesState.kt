package com.practicum.moviesearchapp.ui.names.models

import com.practicum.moviesearchapp.domain.models.Person

sealed interface NamesState {

    data object Loading: NamesState

    data class Error(val errorMessage: String): NamesState

    data class Content(val persons: List<Person>): NamesState

    data class Empty(val message: String): NamesState

}