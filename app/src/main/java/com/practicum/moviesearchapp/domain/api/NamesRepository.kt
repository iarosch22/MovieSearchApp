package com.practicum.moviesearchapp.domain.api

import com.practicum.moviesearchapp.domain.models.Person
import com.practicum.moviesearchapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface NamesRepository {
    fun searchNames(expression: String): Flow<Resource<List<Person>>>
}