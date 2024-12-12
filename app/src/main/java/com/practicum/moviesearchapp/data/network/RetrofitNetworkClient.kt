package com.practicum.moviesearchapp.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.practicum.moviesearchapp.data.NetworkClient
import com.practicum.moviesearchapp.data.dto.details.MovieDetailsRequest
import com.practicum.moviesearchapp.data.dto.cast.MovieFullCastRequest
import com.practicum.moviesearchapp.data.dto.movies.MoviesSearchRequest
import com.practicum.moviesearchapp.data.dto.Response
import com.practicum.moviesearchapp.data.dto.names.NamesSearchRequest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitNetworkClient(private val context: Context) : NetworkClient {

    private val imdbBaseUrl = "https://tv-api.com"

    private val retrofit = Retrofit.Builder()
        .baseUrl(imdbBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val imdbService = retrofit.create(IMDbApiService::class.java)

    override fun doRequest(dto: Any): Response {
        if (!isConnected()) {
            return Response().apply { resultCode = -1 }
        }
        if (
            dto !is MoviesSearchRequest && 
            dto !is MovieDetailsRequest &&
            dto !is MovieFullCastRequest &&
            dto !is NamesSearchRequest
            ) {
            return Response().apply { resultCode = 400 }
        }

        val response = when (dto) {
            is MoviesSearchRequest -> imdbService.searchMovies(dto.expression).execute()
            is MovieDetailsRequest -> imdbService.getMovieDetails(dto.movieId).execute()
            is NamesSearchRequest -> imdbService.searchNames(dto.expression).execute()
            else -> imdbService.getMovieFullCast((dto as MovieFullCastRequest).movieId).execute()
        }

        val body = response.body()
        return body?.apply { resultCode = response.code() } ?: Response().apply { resultCode = response.code() }
    }

    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }

}