package com.practicum.moviesearchapp.data.dto.names

import com.practicum.moviesearchapp.data.dto.Response

data class NamesSearchResponse(
    val searchType: String,
    val expression: String,
    val results: List<PersonDto>
): Response()
