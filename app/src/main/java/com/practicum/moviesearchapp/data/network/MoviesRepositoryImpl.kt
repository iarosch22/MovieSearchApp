package com.practicum.moviesearchapp.data.network

import com.practicum.moviesearchapp.data.NetworkClient
import com.practicum.moviesearchapp.data.dto.details.MovieDetailsRequest
import com.practicum.moviesearchapp.data.dto.details.MovieDetailsResponse
import com.practicum.moviesearchapp.data.dto.cast.MovieFullCastRequest
import com.practicum.moviesearchapp.data.dto.cast.MovieFullCastResponse
import com.practicum.moviesearchapp.data.dto.movies.MoviesSearchRequest
import com.practicum.moviesearchapp.data.dto.movies.MoviesSearchResponse
import com.practicum.moviesearchapp.data.storage.LocalStorage
import com.practicum.moviesearchapp.domain.api.MoviesRepository
import com.practicum.moviesearchapp.domain.models.Movie
import com.practicum.moviesearchapp.domain.models.MovieCastPerson
import com.practicum.moviesearchapp.domain.models.MovieDetails
import com.practicum.moviesearchapp.domain.models.MovieFullCast
import com.practicum.moviesearchapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRepositoryImpl(private val networkClient: NetworkClient, private val localStorage: LocalStorage) : MoviesRepository {

    override fun searchMovies(expression: String): Flow<Resource<List<Movie>>> = flow {
        val response = networkClient.doRequestSuspend(MoviesSearchRequest(expression))

        when(response.resultCode) {
            -1 -> emit(Resource.Error("Проверьте подключение к интернету"))
            200 -> {
                val stored = localStorage.getSavedToFavorites()

                with(response as MoviesSearchResponse) {
                    val data = results.map {
                        Movie(
                            id = it.id,
                            resultType = it.resultType,
                            image = it.image,
                            title = it.title,
                            description = it.description,
                            inFavorite = stored.contains(it.id),
                        )
                    }

                    emit(Resource.Success(data))
                }
            }
            else -> emit(Resource.Error("Ошибка сервера"))
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

    override fun getMovieFullCast(movieId: String): Resource<MovieFullCast> {
        val response = networkClient.doRequest(MovieFullCastRequest(movieId))

        return when(response.resultCode) {
            -1 -> Resource.Error("Проверьте подключение к интернету")
            200 -> {
                with(response as MovieFullCastResponse) {
                    Resource.Success(
                        data = MovieFullCast(
                            imdbId = this.imDbId,
                            fullTitle = this.fullTitle,
                            directors = this.directors.items.map { director ->
                                MovieCastPerson(
                                    id = director.id,
                                    name = director.name,
                                    description = director.description,
                                    image = null
                                )
                            },
                            writers = this.writers.items.map { writer ->
                                MovieCastPerson(
                                    id = writer.id,
                                    name = writer.name,
                                    description = writer.description,
                                    image = null,
                                )
                            },
                            actors = this.actors.map { actor ->
                                MovieCastPerson(
                                    id = actor.id,
                                    name = actor.name,
                                    description = actor.asCharacter,
                                    image = actor.image,
                                )
                            },
                            others = this.others.flatMap { other ->
                                other.items.map { person ->
                                    MovieCastPerson(
                                        id = person.id,
                                        name = person.name,
                                        description = person.description,
                                        image = null
                                    )
                                }
                            }
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