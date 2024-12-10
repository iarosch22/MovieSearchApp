package com.practicum.moviesearchapp.di

import com.practicum.moviesearchapp.core.navigator.Router
import com.practicum.moviesearchapp.core.navigator.impl.RouterImpl
import org.koin.dsl.module

val navigationModule = module {
    val router = RouterImpl()

    single<Router> { router }
    single { router.navigatorHolder }
}