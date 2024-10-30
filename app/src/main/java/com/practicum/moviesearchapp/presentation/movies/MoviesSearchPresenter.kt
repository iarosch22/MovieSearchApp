package com.practicum.moviesearchapp.presentation.movies

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.practicum.moviesearchapp.R
import com.practicum.moviesearchapp.domain.api.MoviesInteractor
import com.practicum.moviesearchapp.domain.models.Movie
import com.practicum.moviesearchapp.ui.movies.models.MoviesState
import com.practicum.moviesearchapp.util.Creator
import moxy.MvpPresenter

class MoviesSearchPresenter(private val context: Context): MvpPresenter<MoviesView>() {

    private var latestSearchText: String? = null

    private val moviesInteractor = Creator.provideMoviesInteractor(context)

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
            viewState.render(
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
                                    MoviesState.Error(errorMessage = context.getString(R.string.something_went_wrong))
                                )
                                viewState.showToast(errorMessage)
                            }
                            movies.isEmpty() -> {
                                renderState(
                                    MoviesState.Empty(message = context.getString(R.string.nothing_found))
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