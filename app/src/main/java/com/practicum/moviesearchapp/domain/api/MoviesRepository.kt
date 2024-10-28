package com.practicum.moviesearchapp.domain.api

import com.practicum.moviesearchapp.domain.models.Movie
import com.practicum.moviesearchapp.util.Resource

interface MoviesRepository {
    fun searchMovies(expression: String): Resource<List<Movie>>
}