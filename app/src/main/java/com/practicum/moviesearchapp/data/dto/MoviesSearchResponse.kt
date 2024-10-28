package com.practicum.moviesearchapp.data.dto

import com.practicum.moviesearchapp.domain.models.Movie

data class MoviesSearchResponse(val searchType: String,
                                val expression: String,
                                val results: List<MovieDto>): Response()