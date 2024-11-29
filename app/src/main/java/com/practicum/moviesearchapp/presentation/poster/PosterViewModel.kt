package com.practicum.moviesearchapp.presentation.poster

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide

class PosterViewModel(imageUrl: String): ViewModel() {

    private val urlLiveData = MutableLiveData(imageUrl)
    fun observeUrl(): LiveData<String> = urlLiveData

}