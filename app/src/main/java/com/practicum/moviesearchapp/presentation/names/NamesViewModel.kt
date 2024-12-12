package com.practicum.moviesearchapp.presentation.names

import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practicum.moviesearchapp.R
import com.practicum.moviesearchapp.domain.api.NamesInteractor
import com.practicum.moviesearchapp.domain.models.Name
import com.practicum.moviesearchapp.ui.names.models.NamesState

class NamesViewModel(private val namesInteractor: NamesInteractor): ViewModel() {

    private val handler = Handler(Looper.getMainLooper())

    private val stateLiveData = MutableLiveData<NamesState>()
    fun observeState(): LiveData<NamesState> = stateLiveData

    private var latestSearchText: String? = null

    fun searchDebounce(changedText: String) {
        if (latestSearchText == changedText) {
            return
        }

        this.latestSearchText = changedText
        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)

        val searchRunnable = Runnable { searchRequest(changedText) }

        val postTime = SystemClock.uptimeMillis() + SEARCH_DEBOUNCE_DELAY
        handler.postAtTime(
            searchRunnable,
            SEARCH_REQUEST_TOKEN,
            postTime,
        )
    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {
            renderState(NamesState.Loading)

            namesInteractor.searchNames(newSearchText, object: NamesInteractor.NamesConsumer {
                override fun consume(foundNames: List<Name>?, errorMessage: String?) {
                    val names = mutableListOf<Name>()
                    if (foundNames != null) names.addAll(foundNames)

                    when {
                        errorMessage != null -> {
                            renderState(
                                NamesState.Error(errorMessage)
                            )
                        }
                        names.isEmpty() -> {
                            renderState(
                                NamesState.Empty(
                                    message = R.string.nothing_found.toString()
                                )
                            )
                        }
                        else -> {
                            renderState(
                                NamesState.Content(
                                    names = names
                                )
                            )
                        }
                    }
                }
            })
        }
    }

    private fun renderState(state: NamesState) {
        stateLiveData.postValue(state)
    }

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 1000L
        private val SEARCH_REQUEST_TOKEN = Any()
    }

}