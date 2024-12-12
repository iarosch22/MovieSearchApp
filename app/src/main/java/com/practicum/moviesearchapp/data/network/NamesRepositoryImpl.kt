package com.practicum.moviesearchapp.data.network

import com.practicum.moviesearchapp.data.NetworkClient
import com.practicum.moviesearchapp.data.dto.names.NamesSearchRequest
import com.practicum.moviesearchapp.data.dto.names.NamesSearchResponse
import com.practicum.moviesearchapp.domain.api.NamesRepository
import com.practicum.moviesearchapp.domain.models.Person
import com.practicum.moviesearchapp.util.Resource

class NamesRepositoryImpl(private val networkClient: NetworkClient): NamesRepository {

    override fun searchNames(expression: String): Resource<List<Person>> {
        val response = networkClient.doRequest(NamesSearchRequest(expression))

        return when(response.resultCode) {
            -1 -> Resource.Error("Проверьте подключение к интернету")
            200 -> {
                with(response as NamesSearchResponse) {
                    Resource.Success( results.map {
                        Person(
                            id = it.id,
                            resultType = it.resultType,
                            image = it.image,
                            title = it.title,
                            description = it.description
                        )
                    })
                }
            }
            else -> Resource.Error("Проверьте подключение к интернету")
        }
    }

}