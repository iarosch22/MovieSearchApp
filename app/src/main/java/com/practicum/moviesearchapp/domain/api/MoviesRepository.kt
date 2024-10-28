package com.practicum.moviesearchapp.domain.api

import com.practicum.moviesearchapp.domain.models.Movie

interface MoviesRepository {
    fun searchMovies(expression: String): List<Movie>
}