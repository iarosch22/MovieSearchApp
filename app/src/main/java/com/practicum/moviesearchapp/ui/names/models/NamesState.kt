package com.practicum.moviesearchapp.ui.names.models

import com.practicum.moviesearchapp.domain.models.Name

sealed interface NamesState {

    data object Loading: NamesState

    data class Error(val errorMessage: String): NamesState

    data class Content(val names: List<Name>): NamesState

    data class Empty(val message: String): NamesState

}