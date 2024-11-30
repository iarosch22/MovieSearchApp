package com.practicum.moviesearchapp.domain.api

import com.practicum.moviesearchapp.domain.models.Movie
import com.practicum.moviesearchapp.domain.models.MovieDetails

interface MoviesInteractor {
    fun searchMovies(expression: String, consumer: MoviesConsumer)
    fun getMovieDetails(movieId: String, consumer: MovieDetailsConsumer)

    interface MoviesConsumer {
        fun consume(foundMovies: List<Movie>?, errorMessage: String?)
    }

    interface MovieDetailsConsumer {
        fun consume(movieDetails: MovieDetails?, errorMessage: String?)
    }

    fun addMovieToFavorites(movie: Movie)

    fun removeMovieFromFavorites(movie: Movie)
}