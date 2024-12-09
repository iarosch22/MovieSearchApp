package com.practicum.moviesearchapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.practicum.moviesearchapp.databinding.FragmentAboutBinding
import com.practicum.moviesearchapp.domain.models.MovieDetails
import com.practicum.moviesearchapp.presentation.about.AboutViewModel
import com.practicum.moviesearchapp.ui.BindingFragment
import com.practicum.moviesearchapp.ui.details.models.AboutState
import com.practicum.moviesearchapp.ui.movieCast.MoviesCastActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AboutFragment: BindingFragment<FragmentAboutBinding>() {

    private val aboutViewModel: AboutViewModel by viewModel {
        parametersOf(requireArguments().getString(MOVIE_ID))
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAboutBinding {
        return FragmentAboutBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        aboutViewModel.observe().observe(viewLifecycleOwner) {
            when(it) {
                is AboutState.Content -> showMovieDetails(it.movieDetails)
                is AboutState.Error -> showError(it.errorMessage)
            }
        }

        binding.showCastBtn.setOnClickListener {
            startActivity(MoviesCastActivity.newInstance(
                context = requireContext(),
                movieId = requireArguments().getString(MOVIE_ID).orEmpty()))
        }
    }

    private fun showMovieDetails(movieDetails: MovieDetails) {
        binding.apply {
            errorMessage.visibility = View.GONE
            details.visibility = View.VISIBLE

            title.text = movieDetails.title
            ratingValue.text = movieDetails.imDbRating
            yearValue.text = movieDetails.year
            genreValue.text = movieDetails.genres
            directionValue.text = movieDetails.directors
            writerValue.text = movieDetails.writers
            actorsValue.text = movieDetails.stars
            plotValue.text = movieDetails.plot
        }
    }

    private fun showError(message: String) {
        binding.apply {
            errorMessage.visibility = View.VISIBLE
            details.visibility = View.GONE
            errorMessage.text = message
        }
    }

    companion object {
        private const val MOVIE_ID = "MOVIE_ID"

        fun newInstance(movieId: String): AboutFragment = AboutFragment().apply {
            arguments = Bundle().apply {
                putString(MOVIE_ID, movieId)
            }
        }
    }

}