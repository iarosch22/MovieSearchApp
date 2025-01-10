package com.practicum.moviesearchapp.domain.impl

import com.practicum.moviesearchapp.domain.api.NamesInteractor
import com.practicum.moviesearchapp.domain.api.NamesRepository
import com.practicum.moviesearchapp.domain.models.Person
import com.practicum.moviesearchapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NamesInteractorImpl(private val repository: NamesRepository) : NamesInteractor {

    override fun searchNames(expression: String): Flow<Pair<List<Person>?, String?>> {
        return repository.searchNames(expression).map { result ->
            when(result) {
                is Resource.Success -> {
                    Pair(result.data, null)
                }
                is Resource.Error -> {
                    Pair(null, result.message)
                }
            }
        }
    }
}