package com.practicum.moviesearchapp.data.network

import com.practicum.moviesearchapp.data.NetworkClient
import com.practicum.moviesearchapp.data.dto.MoviesSearchRequest
import com.practicum.moviesearchapp.data.dto.MoviesSearchResponse
import com.practicum.moviesearchapp.domain.api.MoviesRepository
import com.practicum.moviesearchapp.domain.models.Movie
import com.practicum.moviesearchapp.util.Resource

class MoviesRepositoryImpl(private val networkClient: NetworkClient) : MoviesRepository {

    override fun searchMovies(expression: String): Resource<List<Movie>> {
        val response = networkClient.doRequest(MoviesSearchRequest(expression))

        return when(response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            200 -> {
                Resource.Success((response as MoviesSearchResponse).results.map {
                    Movie(it.id, it.resultType, it.image, it.title, it.description)})
            }

            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }
}