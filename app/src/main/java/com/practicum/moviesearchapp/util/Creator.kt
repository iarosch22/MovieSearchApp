package com.practicum.moviesearchapp.util

import android.content.Context
import com.practicum.moviesearchapp.data.network.MoviesRepositoryImpl
import com.practicum.moviesearchapp.data.network.RetrofitNetworkClient
import com.practicum.moviesearchapp.domain.api.MoviesInteractor
import com.practicum.moviesearchapp.domain.api.MoviesRepository
import com.practicum.moviesearchapp.domain.impl.MoviesInteractorImpl
import com.practicum.moviesearchapp.presentation.movies.MoviesSearchViewModel
import com.practicum.moviesearchapp.presentation.poster.PosterPresenter
import com.practicum.moviesearchapp.presentation.poster.PosterView

object Creator {
    private fun getMoviesRepository(context: Context): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient(context))
    }

    fun provideMoviesInteractor(context: Context): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository(context))
    }

    fun provideMoviesSearchPresenter(context: Context): MoviesSearchViewModel {
        return MoviesSearchViewModel(context = context)
    }

    fun providePosterPresenter(posterView: PosterView, imageUrl: String): PosterPresenter {
        return PosterPresenter(posterView, imageUrl)
    }
}