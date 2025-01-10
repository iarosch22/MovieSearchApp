package com.practicum.moviesearchapp.data.network

import com.practicum.moviesearchapp.data.NetworkClient
import com.practicum.moviesearchapp.data.dto.names.NamesSearchRequest
import com.practicum.moviesearchapp.data.dto.names.NamesSearchResponse
import com.practicum.moviesearchapp.domain.api.NamesRepository
import com.practicum.moviesearchapp.domain.models.Person
import com.practicum.moviesearchapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NamesRepositoryImpl(private val networkClient: NetworkClient): NamesRepository {

    override fun searchNames(expression: String): Flow<Resource<List<Person>>> = flow {
        val response = networkClient.doRequestSuspend(NamesSearchRequest(expression))

        when(response.resultCode) {
            -1 -> emit(Resource.Error("Проверьте подключение к интернету"))
            200 -> {
                with(response as NamesSearchResponse) {
                    val data = results.map {
                        Person(
                            id = it.id,
                            resultType = it.resultType,
                            image = it.image,
                            title = it.title,
                            description = it.description
                        )
                    }
                    emit(Resource.Success(data))
                }
            }
            else -> emit(Resource.Error("Проверьте подключение к интернету"))
        }
    }

}