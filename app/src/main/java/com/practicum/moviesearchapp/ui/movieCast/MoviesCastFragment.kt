package com.practicum.moviesearchapp.ui.movieCast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.practicum.moviesearchapp.databinding.FragmentMoviesCastBinding
import com.practicum.moviesearchapp.presentation.movieCast.MovieCastViewModel
import com.practicum.moviesearchapp.ui.BindingFragment
import com.practicum.moviesearchapp.ui.movieCast.models.MovieCastState
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MoviesCastFragment: BindingFragment<FragmentMoviesCastBinding>() {

    private val movieCastViewModel: MovieCastViewModel by viewModel {
        parametersOf(requireArguments().getString(ARGS_MOVIE_ID))
    }

    private val adapter = ListDelegationAdapter(
        movieClassHeaderDelegate(),
        movieCastPersonDelegate()
    )

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMoviesCastBinding {
        return FragmentMoviesCastBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFullCast.adapter = adapter

        movieCastViewModel.observeState().observe(viewLifecycleOwner) {
            when(it) {
                is MovieCastState.Content -> showContent(it)
                is MovieCastState.Error -> showError(it)
            }
        }
    }

    private fun showContent(state: MovieCastState.Content) {
        binding.errorMessage.visibility = View.GONE

        binding.title.visibility = View.VISIBLE
        binding.rvFullCast.visibility = View.VISIBLE

        binding.title.text = state.fullTitle
        adapter.items = state.items

        adapter.notifyDataSetChanged()
    }

    private fun showError(state: MovieCastState.Error) {
        binding.title.visibility = View.GONE
        binding.rvFullCast.visibility = View.GONE

        binding.errorMessage.visibility = View.VISIBLE
        binding.errorMessage.text = state.message
    }

    companion object {
        private const val ARGS_MOVIE_ID = "movie_id"

        const val TAG = "MoviesCastFragment"

        fun newInstance(movieId: String) = MoviesCastFragment().apply {
            arguments = bundleOf(
                ARGS_MOVIE_ID to  movieId
            )
        }
    }

}