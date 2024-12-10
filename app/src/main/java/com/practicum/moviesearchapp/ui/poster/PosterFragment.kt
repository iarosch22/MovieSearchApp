package com.practicum.moviesearchapp.ui.poster

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.practicum.moviesearchapp.databinding.FragmentPosterBinding
import com.practicum.moviesearchapp.presentation.poster.PosterViewModel
import com.practicum.moviesearchapp.ui.BindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PosterFragment : BindingFragment<FragmentPosterBinding>() {

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

        fun newInstance(posterUrl: String): PosterFragment = PosterFragment().apply {
            arguments = Bundle().apply {
                putString(POSTER_URL, posterUrl)
            }
        }
    }

}