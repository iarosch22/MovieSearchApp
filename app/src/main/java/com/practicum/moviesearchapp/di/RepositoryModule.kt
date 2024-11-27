package com.practicum.moviesearchapp.di

import com.practicum.moviesearchapp.data.network.MoviesRepositoryImpl
import com.practicum.moviesearchapp.data.storage.LocalStorage
import com.practicum.moviesearchapp.domain.api.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MoviesRepository> {
        MoviesRepositoryImpl(get(), get())
    }

    single<LocalStorage> {
        LocalStorage(get())
    }
}