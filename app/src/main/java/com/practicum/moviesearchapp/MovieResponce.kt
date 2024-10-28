package com.practicum.moviesearchapp

class MovieResponce(val searchType: String,
                    val expression: String,
                    val results: List<Movie>) {
}