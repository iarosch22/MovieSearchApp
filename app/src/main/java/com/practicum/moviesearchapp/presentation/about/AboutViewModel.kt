package com.practicum.moviesearchapp.presentation.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicum.moviesearchapp.domain.api.MoviesInteractor
import com.practicum.moviesearchapp.domain.models.MovieDetails
import com.practicum.moviesearchapp.ui.about.models.AboutState
import kotlinx.coroutines.launch

class AboutViewModel(private val movieId: String, private val moviesInteractor: MoviesInteractor): ViewModel() {

    private val stateLiveData = MutableLiveData<AboutState>()
    fun observe(): LiveData<AboutState> = stateLiveData

    init {
        viewModelScope.launch {
            moviesInteractor
                .getMovieDetails(movieId)
                .collect { pair ->
                    processResult(pair.first, pair.second)
                }
        }
    }

    private fun processResult(movieDetails: MovieDetails?, errorMessage: String?) {
        when {
            movieDetails != null -> {
                stateLiveData.postValue(AboutState.Content(movieDetails))
            }
            else -> {
                stateLiveData.postValue(AboutState.Error(errorMessage ?: "Unknown error"))
            }
        }
    }

}