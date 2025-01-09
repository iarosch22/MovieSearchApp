package com.practicum.moviesearchapp.domain.api

import com.practicum.moviesearchapp.domain.models.Person
import kotlinx.coroutines.flow.Flow

interface NamesInteractor {

    fun searchNames(expression: String): Flow<Pair<List<Person>?, String?>>

}