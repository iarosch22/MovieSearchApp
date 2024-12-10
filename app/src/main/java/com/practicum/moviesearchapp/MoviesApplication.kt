package com.practicum.moviesearchapp

import android.app.Application
import com.practicum.moviesearchapp.di.dataModule
import com.practicum.moviesearchapp.di.interactorModule
import com.practicum.moviesearchapp.di.navigationModule
import com.practicum.moviesearchapp.di.repositoryModule
import com.practicum.moviesearchapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MoviesApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MoviesApplication)
            modules(
                dataModule,
                repositoryModule,
                interactorModule,
                viewModelModule,
                navigationModule
            )
        }
    }

}