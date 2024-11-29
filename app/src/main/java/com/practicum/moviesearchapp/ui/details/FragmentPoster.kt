package com.practicum.moviesearchapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.practicum.moviesearchapp.databinding.FragmentPosterBinding
import com.practicum.moviesearchapp.presentation.poster.PosterViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class FragmentPoster : BindingFragment<FragmentPosterBinding>() {

    private val posterViewModel: PosterViewModel by viewModel {
        parametersOf(requireArguments().getString(POSTER_URL))
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPosterBinding {
        return FragmentPosterBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        posterViewModel.observeUrl().observe(viewLifecycleOwner) {
            showPoster(it)
        }
    }

    private fun showPoster(posterUrl: String) {
        context?.let {
            Glide.with(it)
                .load(posterUrl)
                .into(binding.poster)}
    }

    companion object {
        private const val POSTER_URL = "POSTER_URL"

        fun newInstance(posterUrl: String): FragmentPoster = FragmentPoster().apply {
            arguments = Bundle().apply {
                putString(POSTER_URL, posterUrl)
            }
        }
    }

}