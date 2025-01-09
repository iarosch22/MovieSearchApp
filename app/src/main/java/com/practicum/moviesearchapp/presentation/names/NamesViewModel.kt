package com.practicum.moviesearchapp.presentation.names

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicum.moviesearchapp.R
import com.practicum.moviesearchapp.domain.api.NamesInteractor
import com.practicum.moviesearchapp.domain.models.Person
import com.practicum.moviesearchapp.ui.names.models.NamesState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NamesViewModel(private val namesInteractor: NamesInteractor): ViewModel() {

    private val stateLiveData = MutableLiveData<NamesState>()
    fun observeState(): LiveData<NamesState> = stateLiveData

    private var latestSearchText: String? = null

    private var searchJob: Job? = null

    fun searchDebounce(changedText: String) {
        if (latestSearchText == changedText) {
            return
        }

        latestSearchText = changedText

        searchJob?.cancel()

        searchJob = viewModelScope.launch {
            delay(SEARCH_DEBOUNCE_DELAY)
            searchRequest(changedText)
        }
    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {
            renderState(NamesState.Loading)

            viewModelScope.launch {
                namesInteractor
                    .searchNames(newSearchText)
                    .collect { pair ->
                        processResult(pair.first, pair.second)
                    }
            }
        }
    }

    private fun processResult(foundNames: List<Person>?, errorMessage: String?) {
        val persons = mutableListOf<Person>()
        if (foundNames != null) persons.addAll(foundNames)

        when {
            errorMessage != null -> renderState(NamesState.Error(errorMessage = errorMessage))

            persons.isEmpty() -> renderState(NamesState.Empty(message = R.string.nothing_found.toString()))
            else -> renderState(NamesState.Content(persons = persons))
        }
    }

    private fun renderState(state: NamesState) {
        stateLiveData.postValue(state)
    }

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 1000L
    }

}