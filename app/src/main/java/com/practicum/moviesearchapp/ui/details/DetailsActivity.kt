package com.practicum.moviesearchapp.ui.details

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.practicum.moviesearchapp.util.Creator
import com.practicum.moviesearchapp.R
import com.practicum.moviesearchapp.presentation.poster.PosterViewModel
import com.practicum.moviesearchapp.presentation.poster.PosterView

class DetailsActivity : Activity(), PosterView {

    private lateinit var posterPresenter: PosterViewModel

    private lateinit var poster: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val imageUrl = intent.extras?.getString("poster", "") ?: ""
        posterPresenter = Creator.providePosterPresenter(this, imageUrl)

        setContentView(R.layout.activity_poster)
        poster = findViewById(R.id.poster)

    }

    override fun setupPosterImage(url: String) {
        Glide.with(applicationContext)
            .load(url)
            .into(poster)
    }
}