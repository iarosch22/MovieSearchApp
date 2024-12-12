package com.practicum.moviesearchapp.data.network

import com.practicum.moviesearchapp.data.dto.details.MovieDetailsResponse
import com.practicum.moviesearchapp.data.dto.cast.MovieFullCastResponse
import com.practicum.moviesearchapp.data.dto.movies.MoviesSearchResponse
import com.practicum.moviesearchapp.data.dto.names.NamesSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IMDbApiService {
    @GET("/en/API/SearchMovie/k_zcuw1ytf/{expression}")
    fun searchMovies(@Path("expression") expression: String): Call<MoviesSearchResponse>

    @GET("/en/API/Title/k_zcuw1ytf/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: String): Call<MovieDetailsResponse>

    @GET("en/API/FullCast/k_zcuw1ytf/{movie_id}")
    fun getMovieFullCast(@Path("movie_id") movieId: String): Call<MovieFullCastResponse>

    @GET("en/API/SearchName/k_zcuw1ytf/{expression}")
    fun searchNames(@Path("expression") expression: String): Call<NamesSearchResponse>
}