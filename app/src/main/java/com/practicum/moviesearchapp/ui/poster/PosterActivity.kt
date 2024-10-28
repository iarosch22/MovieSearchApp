package com.practicum.moviesearchapp.ui.poster

import android.app.Activity
import android.os.Bundle
import com.practicum.moviesearchapp.util.Creator
import com.practicum.moviesearchapp.R

class PosterActivity : Activity() {

    private val posterController = Creator.providePosterController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poster)
        posterController.onCreate()
    }
}