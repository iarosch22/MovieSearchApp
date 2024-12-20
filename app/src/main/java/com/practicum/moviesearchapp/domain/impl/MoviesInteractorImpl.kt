package com.practicum.moviesearchapp.domain.impl

import com.practicum.moviesearchapp.domain.api.MoviesInteractor
import com.practicum.moviesearchapp.domain.api.MoviesRepository
import com.practicum.moviesearchapp.domain.models.Movie
import com.practicum.moviesearchapp.util.Resource
import java.util.concurrent.Executors

class MoviesInteractorImpl(private val repository: MoviesRepository) : MoviesInteractor {

    private val executor = Executors.newCachedThreadPool()

    override fun searchMovies(expression: String, consumer: MoviesInteractor.MoviesConsumer) {
        executor.execute {
            when(val resource = repository.searchMovies(expression)) {
                is Resource.Error -> { consumer.consume(null, resource.message)}
                is Resource.Success -> { consumer.consume(resource.data, null)}
            }
        }
    }

    override fun getMovieDetails(movieId: String, consumer: MoviesInteractor.MovieDetailsConsumer) {
        executor.execute {
            when(val resource = repository.getMovieDetails(movieId)) {
                is Resource.Error -> { consumer.consume(null, resource.message) }
                is Resource.Success -> { consumer.consume(resource.data, null) }
            }
        }
    }

    override fun getMovieFullCast(
        movieId: String,
        consumer: MoviesInteractor.MovieFullCastConsumer
    ) {
        executor.execute {
            when(val resource = repository.getMovieFullCast(movieId)) {
                is Resource.Error -> { consumer.consume(null, resource.message) }
                is Resource.Success -> { consumer.consume(resource.data, null) }
            }
        }
    }

    override fun addMovieToFavorites(movie: Movie) {
        repository.addMovieToFavorites(movie)
    }

    override fun removeMovieFromFavorites(movie: Movie) {
        repository.removeMovieFromFavorites(movie)
    }
}