package com.practicum.moviesearchapp

import android.app.Application
import com.practicum.moviesearchapp.presentation.movies.MoviesSearchPresenter

class MoviesApplication: Application() {

    var moviesSearchPresenter: MoviesSearchPresenter? = null

}