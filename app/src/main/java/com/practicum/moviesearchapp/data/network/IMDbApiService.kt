package com.practicum.moviesearchapp.data.network

import com.practicum.moviesearchapp.data.dto.cast.MovieFullCastResponse
import com.practicum.moviesearchapp.data.dto.details.MovieDetailsResponse
import com.practicum.moviesearchapp.data.dto.movies.MoviesSearchResponse
import com.practicum.moviesearchapp.data.dto.names.NamesSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface IMDbApiService {

    @GET("/en/API/SearchMovie/k_zcuw1ytf/{expression}")
    suspend fun searchMovies(@Path("expression") expression: String): MoviesSearchResponse

    @GET("/en/API/Title/k_zcuw1ytf/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: String): MovieDetailsResponse

    @GET("en/API/FullCast/k_zcuw1ytf/{movie_id}")
    suspend fun getMovieFullCast(@Path("movie_id") movieId: String): MovieFullCastResponse

    @GET("en/API/SearchName/k_zcuw1ytf/{expression}")
    suspend fun searchNames(@Path("expression") expression: String): NamesSearchResponse

}