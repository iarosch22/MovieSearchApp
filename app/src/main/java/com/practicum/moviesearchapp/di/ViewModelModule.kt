package com.practicum.moviesearchapp.di

import com.practicum.moviesearchapp.presentation.about.AboutViewModel
import com.practicum.moviesearchapp.presentation.history.HistoryViewModel
import com.practicum.moviesearchapp.presentation.movieCast.MovieCastViewModel
import com.practicum.moviesearchapp.presentation.movies.MoviesSearchViewModel
import com.practicum.moviesearchapp.presentation.names.NamesViewModel
import com.practicum.moviesearchapp.presentation.poster.PosterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MoviesSearchViewModel(get())
    }

    viewModel{
        NamesViewModel(get())
    }

    viewModel {(movieId: String) ->
        AboutViewModel(movieId, get())
    }

    viewModel {(imageUrl: String) ->
        PosterViewModel(imageUrl)
    }

    viewModel {(movieId: String) ->
        MovieCastViewModel(movieId, get())
    }

    viewModel {
        HistoryViewModel(get())
    }
}