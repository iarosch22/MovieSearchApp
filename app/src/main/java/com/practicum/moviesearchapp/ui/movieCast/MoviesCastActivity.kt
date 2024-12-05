package com.practicum.moviesearchapp.ui.movieCast

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.practicum.moviesearchapp.R

class MoviesCastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_cast)
    }

    companion object {
        private val MOVIE_ID = "movie_id"

        fun newInstance(context: Context): Intent {
            return Intent(context, MoviesCastActivity::class.java)
        }
    }

}