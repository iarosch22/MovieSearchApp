package com.practicum.moviesearchapp.domain.db

import com.practicum.moviesearchapp.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface HistoryInteractor {

    fun historyMovies(): Flow<List<Movie>>

}