package com.practicum.moviesearchapp.data.network

import com.practicum.moviesearchapp.data.NetworkClient
import com.practicum.moviesearchapp.data.dto.MovieDetailsRequest
import com.practicum.moviesearchapp.data.dto.MovieDetailsResponse
import com.practicum.moviesearchapp.data.dto.MoviesSearchRequest
import com.practicum.moviesearchapp.data.dto.MoviesSearchResponse
import com.practicum.moviesearchapp.data.storage.LocalStorage
import com.practicum.moviesearchapp.domain.api.MoviesRepository
import com.practicum.moviesearchapp.domain.models.Movie
import com.practicum.moviesearchapp.domain.models.MovieDetails
import com.practicum.moviesearchapp.util.Resource

class MoviesRepositoryImpl(private val networkClient: NetworkClient, private val localStorage: LocalStorage) : MoviesRepository {

    override fun searchMovies(expression: String): Resource<List<Movie>> {
        val response = networkClient.doRequest(MoviesSearchRequest(expression))

        return when(response.resultCode) {
            -1 -> Resource.Error("Проверьте подключение к интернету")
            200 -> {
                val stored = localStorage.getSavedToFavorites()

                with(response as MoviesSearchResponse) {
                    Resource.Success( results.map {
                        Movie(
                            id = it.id,
                            resultType = it.resultType,
                            image = it.image,
                            title = it.title,
                            description = it.description,
                            inFavorite = stored.contains(it.id),
                        )
                    })
                }
            }
            else -> Resource.Error("Ошибка сервера")
        }
    }

    override fun getMovieDetails(movieId: String): Resource<MovieDetails> {
        val response = networkClient.doRequest(MovieDetailsRequest(movieId))

        return when(response.resultCode) {
            -1 -> Resource.Error("Проверьте подключение к интернету")
            200 -> {
                with(response as MovieDetailsResponse) {
                    Resource.Success(
                        MovieDetails(
                            id = id,
                            title = title,
                            imDbRating = imDbRating,
                            year = year,
                            countries = countries,
                            genres = genres,
                            directors = directors,
                            writers = writers,
                            stars = stars,
                            plot = plot,
                        )
                    )
                }
            }
            else -> Resource.Error("Ошибка сервера")
        }
    }

    override fun addMovieToFavorites(movie: Movie) {
        localStorage.addToFavorites(movie.id)
    }

    override fun removeMovieFromFavorites(movie: Movie) {
        localStorage.removeFromFavorites(movie.id)
    }
}