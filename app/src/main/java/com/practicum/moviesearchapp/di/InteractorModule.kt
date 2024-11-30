package com.practicum.moviesearchapp.di

import com.practicum.moviesearchapp.data.storage.LocalStorage
import com.practicum.moviesearchapp.domain.api.MoviesInteractor
import com.practicum.moviesearchapp.domain.impl.MoviesInteractorImpl
import org.koin.dsl.module

val interactorModule = module {

    single<MoviesInteractor> {
        MoviesInteractorImpl(get())
    }

    single<LocalStorage> {
        LocalStorage(get())
    }

}