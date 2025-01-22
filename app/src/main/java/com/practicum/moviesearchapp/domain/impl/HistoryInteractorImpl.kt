package com.practicum.moviesearchapp.domain.impl

import com.practicum.moviesearchapp.domain.db.HistoryInteractor
import com.practicum.moviesearchapp.domain.db.HistoryRepository
import com.practicum.moviesearchapp.domain.models.Movie
import kotlinx.coroutines.flow.Flow

class HistoryInteractorImpl(
    private val historyRepository: HistoryRepository
): HistoryInteractor {

    override fun historyMovies(): Flow<List<Movie>> {
        return historyRepository.historyMovies()
    }

}