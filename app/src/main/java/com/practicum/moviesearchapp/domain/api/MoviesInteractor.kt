package com.practicum.moviesearchapp.domain.api

import com.practicum.moviesearchapp.domain.models.Movie
import com.practicum.moviesearchapp.domain.models.MovieDetails
import com.practicum.moviesearchapp.domain.models.MovieFullCast
import kotlinx.coroutines.flow.Flow

interface MoviesInteractor {

    fun searchMovies(expression: String): Flow<Pair<List<Movie>?, String?>>

    fun getMovieDetails(movieId: String): Flow<Pair<MovieDetails?, String?>>

    fun getMovieFullCast(movieId: String): Flow<Pair<MovieFullCast?, String?>>

    fun addMovieToFavorites(movie: Movie)

    fun removeMovieFromFavorites(movie: Movie)

}