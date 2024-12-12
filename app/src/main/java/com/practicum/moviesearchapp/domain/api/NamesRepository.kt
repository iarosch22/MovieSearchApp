package com.practicum.moviesearchapp.domain.api

import com.practicum.moviesearchapp.domain.models.Person
import com.practicum.moviesearchapp.util.Resource

interface NamesRepository {
    fun searchNames(expression: String): Resource<List<Person>>
}