package com.practicum.moviesearchapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {

    @GET("/en/API/SearchMovie/k_zcuw1ytf/{expression}")

    fun findMovie(@Path("expression") expression: String): Call<MovieResponce>

}