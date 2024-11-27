package com.practicum.moviesearchapp.di

import com.practicum.moviesearchapp.presentation.movies.MoviesSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{
        MoviesSearchViewModel(get())
    }
}