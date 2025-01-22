package com.practicum.moviesearchapp.di

import com.practicum.moviesearchapp.data.storage.LocalStorage
import com.practicum.moviesearchapp.domain.api.MoviesInteractor
import com.practicum.moviesearchapp.domain.api.NamesInteractor
import com.practicum.moviesearchapp.domain.db.HistoryInteractor
import com.practicum.moviesearchapp.domain.impl.HistoryInteractorImpl
import com.practicum.moviesearchapp.domain.impl.MoviesInteractorImpl
import com.practicum.moviesearchapp.domain.impl.NamesInteractorImpl
import org.koin.dsl.module

val interactorModule = module {

    single<MoviesInteractor> {
        MoviesInteractorImpl(get())
    }

    single<NamesInteractor> {
        NamesInteractorImpl(get())
    }

    single<LocalStorage> {
        LocalStorage(get())
    }

    single<HistoryInteractor> {
        HistoryInteractorImpl( get() )
    }

}