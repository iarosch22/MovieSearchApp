package com.practicum.moviesearchapp.presentation.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practicum.moviesearchapp.domain.api.MoviesInteractor
import com.practicum.moviesearchapp.domain.models.MovieDetails
import com.practicum.moviesearchapp.ui.about.models.AboutState

class AboutViewModel(private val movieId: String, private val moviesInteractor: MoviesInteractor): ViewModel() {

    private val stateLiveData = MutableLiveData<AboutState>()
    fun observe(): LiveData<AboutState> = stateLiveData

    init {
        moviesInteractor.getMovieDetails(movieId, object : MoviesInteractor.MovieDetailsConsumer {
            override fun consume(movieDetails: MovieDetails?, errorMessage: String?) {
                when {
                    movieDetails != null -> {
                        stateLiveData.postValue(AboutState.Content(movieDetails))
                    }
                    else -> {
                        stateLiveData.postValue(AboutState.Error(errorMessage ?: "Unknown error"))
                    }
                }
            }

        })
    }

}