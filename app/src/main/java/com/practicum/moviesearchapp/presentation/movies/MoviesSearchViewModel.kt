package com.practicum.moviesearchapp.presentation.movies

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.practicum.moviesearchapp.R
import com.practicum.moviesearchapp.domain.api.MoviesInteractor
import com.practicum.moviesearchapp.domain.models.Movie
import com.practicum.moviesearchapp.ui.movies.models.MoviesState
import com.practicum.moviesearchapp.util.Creator

class MoviesSearchViewModel(application: Application): AndroidViewModel(application) {

    private var latestSearchText: String? = null

    private val moviesInteractor = Creator.provideMoviesInteractor(getApplication<Application>())

    private var lastSearchText: String? = null

    private val movies = ArrayList<Movie>()

    private val handler = Handler(Looper.getMainLooper())

    private val searchRunnable = Runnable {
        val newSearchText = lastSearchText ?: ""
        searchRequest(newSearchText)
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(searchRunnable)
    }

    fun searchDebounce(changedText: String) {
        if (latestSearchText == changedText) {
            return
        }
        this.lastSearchText = changedText
        handler.removeCallbacks(searchRunnable)
        handler.postDelayed(searchRunnable, SEARCH_DEBOUNCE_DELAY)
    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {
            renderState(
                MoviesState.Loading
            )

            moviesInteractor.searchMovies(newSearchText, object : MoviesInteractor.MoviesConsumer {
                override fun consume(foundMovies: List<Movie>?, errorMessage: String?) {
                    handler.post {
                        if (foundMovies != null) {
                            movies.clear()
                            movies.addAll(foundMovies)
                        }

                        when {
                            errorMessage != null -> {
                                renderState(
                                    MoviesState.Error(errorMessage = getApplication<Application>().getString(R.string.something_went_wrong))
                                )
                                viewState.showToast(errorMessage)
                            }
                            movies.isEmpty() -> {
                                renderState(
                                    MoviesState.Empty(message = getApplication<Application>().getString(R.string.nothing_found))
                                )
                            }
                            else -> {
                                renderState(
                                    MoviesState.Content(movies = movies)
                                )
                            }
                        }
                    }
                }
            })
        }
    }

    private fun renderState(state: MoviesState) {
        viewState.render(state)
    }

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
    }

}