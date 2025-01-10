package com.practicum.moviesearchapp.domain.impl

import com.practicum.moviesearchapp.domain.api.MoviesInteractor
import com.practicum.moviesearchapp.domain.api.MoviesRepository
import com.practicum.moviesearchapp.domain.models.Movie
import com.practicum.moviesearchapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.Executors

class MoviesInteractorImpl(private val repository: MoviesRepository) : MoviesInteractor {

    private val executor = Executors.newCachedThreadPool()

    override fun searchMovies(expression: String): Flow<Pair<List<Movie>?, String?>> {
        return repository.searchMovies(expression).map { result ->
            when(result) {
                is Resource.Error -> Pair(null, result.message)
                is Resource.Success -> Pair(result.data, null)
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