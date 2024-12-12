package com.practicum.moviesearchapp.domain.api

import com.practicum.moviesearchapp.domain.models.Movie
import com.practicum.moviesearchapp.domain.models.MovieDetails
import com.practicum.moviesearchapp.domain.models.MovieFullCast
import com.practicum.moviesearchapp.domain.models.Name

interface MoviesInteractor {
    fun searchMovies(expression: String, consumer: MoviesConsumer)
    fun searchNames(expression: String, consumer: NamesConsumer)
    fun getMovieDetails(movieId: String, consumer: MovieDetailsConsumer)
    fun getMovieFullCast(movieId: String, consumer: MovieFullCastConsumer)

    interface MoviesConsumer {
        fun consume(foundMovies: List<Movie>?, errorMessage: String?)
    }

    interface NamesConsumer {
        fun consume(foundNames: List<Name>?, errorMessage: String?)
    }

    interface MovieDetailsConsumer {
        fun consume(movieDetails: MovieDetails?, errorMessage: String?)
    }

    interface MovieFullCastConsumer {
        fun consume(movieFullCast: MovieFullCast?, errorMessage: String?)
    }

    fun addMovieToFavorites(movie: Movie)

    fun removeMovieFromFavorites(movie: Movie)
}