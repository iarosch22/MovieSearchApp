package com.practicum.moviesearchapp.data.dto.movies

import com.practicum.moviesearchapp.data.dto.Response

data class MoviesSearchResponse(val searchType: String,
                                val expression: String,
                                val results: List<MovieDto>): Response()