package com.practicum.moviesearchapp.presentation.poster

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide

class PosterViewModel(private val imageUrl: String): ViewModel() {

    fun setupPosterImage(url: String) {
        Glide.with(applicationContext)
            .load(url)
            .into(poster)
    }

    private val urlLiveData = MutableLiveData(imageUrl)
    fun observeUrl(): LiveData<String> = urlLiveData

}