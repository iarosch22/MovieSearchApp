package com.practicum.moviesearchapp.di

import android.content.Context
import com.practicum.moviesearchapp.data.NetworkClient
import com.practicum.moviesearchapp.data.network.IMDbApiService
import com.practicum.moviesearchapp.data.network.RetrofitNetworkClient
import com.practicum.moviesearchapp.data.storage.LocalStorage
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single<IMDbApiService> {
        Retrofit.Builder()
            .baseUrl("https://tv-api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IMDbApiService::class.java)
    }

    single {
        androidContext()
            .getSharedPreferences("local_storage", Context.MODE_PRIVATE)
    }

    single<LocalStorage> {
        LocalStorage(get())
    }

    single<NetworkClient> {
        RetrofitNetworkClient(androidContext())
    }
}