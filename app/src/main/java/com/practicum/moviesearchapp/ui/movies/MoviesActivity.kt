package com.practicum.moviesearchapp.ui.movies

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.practicum.moviesearchapp.util.Creator
import com.practicum.moviesearchapp.ui.poster.PosterActivity
import com.practicum.moviesearchapp.R
import com.practicum.moviesearchapp.presentation.movies.MoviesView

class MoviesActivity : Activity(), MoviesView {

    companion object {
        private const val CLICK_DEBOUNCE_DELAY = 1000L
    }

    private val adapter = MoviesAdapter {
        if (clickDebounce()) {
            val intent = Intent(this, PosterActivity::class.java)
            intent.putExtra("poster", it.image)
            startActivity(intent)
        }
    }

    private var isClickAllowed = true

    private val handler = Handler(Looper.getMainLooper())

    private val moviesSearchPresenter = Creator.provideMoviesSearchPresenter(this, adapter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        moviesSearchPresenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        moviesSearchPresenter.onDestroy()
    }

    private fun clickDebounce() : Boolean {
        val current = isClickAllowed
        if (isClickAllowed) {
            isClickAllowed = false
            handler.postDelayed({ isClickAllowed = true }, CLICK_DEBOUNCE_DELAY)
        }
        return current
    }

}