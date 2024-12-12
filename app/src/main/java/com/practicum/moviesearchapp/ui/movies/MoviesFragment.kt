package com.practicum.moviesearchapp.ui.movies

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.practicum.moviesearchapp.R
import com.practicum.moviesearchapp.databinding.FragmentMoviesBinding
import com.practicum.moviesearchapp.domain.models.Movie
import com.practicum.moviesearchapp.presentation.movies.MoviesSearchViewModel
import com.practicum.moviesearchapp.ui.BindingFragment
import com.practicum.moviesearchapp.ui.details.DetailsFragment
import com.practicum.moviesearchapp.ui.movies.models.MoviesState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment: BindingFragment<FragmentMoviesBinding>() {

    private val adapter = MoviesAdapter(
        object : MoviesAdapter.MovieClickListener {
            override fun onMovieClick(movie: Movie) {
                if (clickDebounce()) {
                    findNavController().navigate(
                        R.id.action_moviesFragment_to_detailsFragment,
                        DetailsFragment.createArgs(movie.image, movie.id)
                    )
                }
            }

            override fun onFavoriteToggleClick(movie: Movie) {
                viewModel.toggleFavorite(movie)
            }

        }
    )

    private var isClickAllowed = true

    private val handler = Handler(Looper.getMainLooper())

    private var textWatcher: TextWatcher? = null

    private val viewModel by viewModel<MoviesSearchViewModel>()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMoviesBinding {
        return FragmentMoviesBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.locationsRV.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.locationsRV.adapter = adapter

        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.searchDebounce(changedText = s?.toString() ?: "")
            }

            override fun afterTextChanged(s: Editable?) {
            }
        }

        textWatcher?.let { binding.queryInput.addTextChangedListener(it) }

        viewModel.observeState().observe(viewLifecycleOwner) {
            render(it)
        }

        viewModel.observeShowToast().observe(viewLifecycleOwner) { toast ->
            if (toast != null) {
                showToast(toast)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        textWatcher?.let { binding.queryInput.removeTextChangedListener(it) }
    }

    private fun clickDebounce() : Boolean {
        val current = isClickAllowed
        if (isClickAllowed) {
            isClickAllowed = false
            handler.postDelayed({ isClickAllowed = true }, CLICK_DEBOUNCE_DELAY)
        }
        return current
    }

    private fun showLoading() {
        binding.locationsRV.isVisible = false
        binding.placeholderMessage.isVisible = false
        binding.progressBar.isVisible = true
    }

    private fun showError(errorMessage: String) {
        binding.locationsRV.isVisible = false
        binding.placeholderMessage.isVisible = true
        binding.progressBar.isVisible = false

        binding.placeholderMessage.text = errorMessage
    }

    private fun showEmpty(errorMessage: String) {
        showError(errorMessage)
    }

    private fun showContent(movies: List<Movie>) {
        binding.locationsRV.isVisible = true
        binding.placeholderMessage.isVisible = false
        binding.progressBar.isVisible = false

        adapter.movies.clear()
        adapter.movies.addAll(movies)
        adapter.notifyDataSetChanged()
    }

    private fun render(state: MoviesState) {
        when(state) {
            is MoviesState.Content -> showContent(state.movies)
            is MoviesState.Empty -> showEmpty(state.message)
            is MoviesState.Error -> showError(state.errorMessage)
            MoviesState.Loading -> showLoading()
        }
    }

    private fun showToast(additionalMessage: String) {
        Toast.makeText(requireContext(), additionalMessage, Toast.LENGTH_LONG).show()
    }

    companion object {
        private const val CLICK_DEBOUNCE_DELAY = 1000L
    }

}