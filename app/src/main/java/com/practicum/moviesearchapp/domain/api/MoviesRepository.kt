package com.practicum.moviesearchapp.domain.api

import com.practicum.moviesearchapp.domain.models.Movie
import com.practicum.moviesearchapp.domain.models.MovieDetails
import com.practicum.moviesearchapp.domain.models.MovieFullCast
import com.practicum.moviesearchapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun searchMovies(expression: String): Flow<Resource<List<Movie>>>

    fun getMovieDetails(movieId: String): Resource<MovieDetails>

    fun getMovieFullCast(movieId: String): Resource<MovieFullCast>

    fun addMovieToFavorites(movie: Movie)

    fun removeMovieFromFavorites(movie: Movie)

}