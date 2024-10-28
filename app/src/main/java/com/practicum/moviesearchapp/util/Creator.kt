package com.practicum.moviesearchapp.util

import android.app.Activity
import android.content.Context
import com.practicum.moviesearchapp.data.network.MoviesRepositoryImpl
import com.practicum.moviesearchapp.data.network.RetrofitNetworkClient
import com.practicum.moviesearchapp.domain.api.MoviesInteractor
import com.practicum.moviesearchapp.domain.api.MoviesRepository
import com.practicum.moviesearchapp.domain.impl.MoviesInteractorImpl
import com.practicum.moviesearchapp.presentation.MoviesSearchController
import com.practicum.moviesearchapp.ui.movies.MoviesAdapter
import com.practicum.moviesearchapp.ui.poster.PosterController

object Creator {
    private fun getMoviesRepository(context: Context): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient(context))
    }

    fun provideMoviesInteractor(context: Context): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository(context))
    }

    fun provideMoviesSearchController(activity: Activity, adapter: MoviesAdapter): MoviesSearchController {
        return MoviesSearchController(activity, adapter)
    }

    fun providePosterController(activity: Activity): PosterController {
        return PosterController(activity)
    }
}