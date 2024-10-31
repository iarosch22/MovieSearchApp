package com.practicum.moviesearchapp

import android.app.Application
import com.practicum.moviesearchapp.presentation.movies.MoviesSearchViewModel

class MoviesApplication: Application() {

    var moviesSearchViewModel: MoviesSearchViewModel? = null

}