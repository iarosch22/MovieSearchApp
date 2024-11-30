package com.practicum.moviesearchapp.domain.api

import com.practicum.moviesearchapp.domain.models.Movie
import com.practicum.moviesearchapp.domain.models.MovieDetails
import com.practicum.moviesearchapp.util.Resource

interface MoviesRepository {
    fun searchMovies(expression: String): Resource<List<Movie>>
    fun getMovieDetails(movieId: String): Resource<MovieDetails>
    fun addMovieToFavorites(movie: Movie)
    fun removeMovieFromFavorites(movie: Movie)
}