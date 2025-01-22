package com.practicum.moviesearchapp.di

import com.practicum.moviesearchapp.data.HistoryRepositoryImpl
import com.practicum.moviesearchapp.data.converters.MovieDbConvertor
import com.practicum.moviesearchapp.data.network.MoviesRepositoryImpl
import com.practicum.moviesearchapp.data.network.NamesRepositoryImpl
import com.practicum.moviesearchapp.data.storage.LocalStorage
import com.practicum.moviesearchapp.domain.api.MoviesRepository
import com.practicum.moviesearchapp.domain.api.NamesRepository
import com.practicum.moviesearchapp.domain.db.HistoryRepository
import com.practicum.moviesearchapp.domain.impl.NamesInteractorImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<MoviesRepository> {
        MoviesRepositoryImpl( get(), get(), get(), get() )
    }

    single<NamesRepository> {
        NamesRepositoryImpl( get() )
    }

    single<LocalStorage> {
        LocalStorage( get() )
    }

    factory { MovieDbConvertor() }

    single<HistoryRepository> {
        HistoryRepositoryImpl( get(), get() )
    }
}