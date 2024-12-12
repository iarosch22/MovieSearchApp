package com.practicum.moviesearchapp.domain.api

import com.practicum.moviesearchapp.domain.models.Movie
import com.practicum.moviesearchapp.domain.models.MovieDetails
import com.practicum.moviesearchapp.domain.models.MovieFullCast
import com.practicum.moviesearchapp.domain.models.Name
import com.practicum.moviesearchapp.util.Resource

interface MoviesRepository {
    fun searchMovies(expression: String): Resource<List<Movie>>
    fun searchNames(expression: String): Resource<List<Name>>
    fun getMovieDetails(movieId: String): Resource<MovieDetails>
    fun getMovieFullCast(movieId: String): Resource<MovieFullCast>
    fun addMovieToFavorites(movie: Movie)
    fun removeMovieFromFavorites(movie: Movie)
}