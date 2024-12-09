package com.practicum.moviesearchapp.ui.movieCast

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.practicum.moviesearchapp.databinding.ActivityMoviesCastBinding
import com.practicum.moviesearchapp.presentation.movieCast.MovieCastViewModel
import com.practicum.moviesearchapp.ui.movieCast.models.MovieCastState
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MoviesCastActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviesCastBinding

    private val movieCastViewModel: MovieCastViewModel by viewModel {
        parametersOf(intent.getStringExtra(ARGS_MOVIE_ID))
    }

    private val adapter = ListDelegationAdapter(
        movieClassHeaderDelegate(),
        movieCastPersonDelegate()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesCastBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvFullCast.adapter = adapter

        movieCastViewModel.observeState().observe(this) {
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

        fun newInstance(context: Context, movieId: String): Intent {
            return Intent(context, MoviesCastActivity::class.java).apply {
                putExtra(ARGS_MOVIE_ID, movieId)
            }
        }
    }

}