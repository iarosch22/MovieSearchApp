package com.practicum.moviesearchapp.ui.movies

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.practicum.moviesearchapp.MoviesApplication
import com.practicum.moviesearchapp.R
import com.practicum.moviesearchapp.domain.models.Movie
import com.practicum.moviesearchapp.presentation.movies.MoviesSearchViewModel
import com.practicum.moviesearchapp.presentation.movies.MoviesView
import com.practicum.moviesearchapp.ui.movies.models.MoviesState
import com.practicum.moviesearchapp.ui.poster.PosterActivity
import com.practicum.moviesearchapp.util.Creator
import moxy.MvpActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class MoviesActivity : MvpActivity(), MoviesView {

    private val adapter = MoviesAdapter {
        if (clickDebounce()) {
            val intent = Intent(this, PosterActivity::class.java)
            intent.putExtra("poster", it.image)
            startActivity(intent)
        }
    }

    private var isClickAllowed = true

    private val handler = Handler(Looper.getMainLooper())

    private var textWatcher: TextWatcher? = null

    @InjectPresenter
    lateinit var moviesSearchViewModel : MoviesSearchViewModel

    @ProvidePresenter
    fun providePresenter(): MoviesSearchViewModel {
        return Creator.provideMoviesSearchPresenter(context = this.applicationContext)
    }

    private lateinit var queryInput: EditText
    private lateinit var placeholderMessage: TextView
    private lateinit var moviesList: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        //moviesSearchPresenter?.attachView(this)

        placeholderMessage = findViewById(R.id.placeholderMessage)
        queryInput = findViewById(R.id.queryInput)
        moviesList = findViewById(R.id.locations)
        progressBar = findViewById(R.id.progressBar)

        moviesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        moviesList.adapter = adapter

        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                moviesSearchViewModel?.searchDebounce(changedText = s?.toString() ?: "")
            }

            override fun afterTextChanged(s: Editable?) {
            }

        }

        textWatcher?.let { queryInput.addTextChangedListener(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        textWatcher?.let { queryInput.removeTextChangedListener(it) }
        moviesSearchViewModel?.onDestroy()

        if (isFinishing) {
            (this.application as? MoviesApplication)?.moviesSearchViewModel = null
        }
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
        moviesList.isVisible = false
        placeholderMessage.isVisible = false
        progressBar.isVisible = true
    }

    private fun showError(errorMessage: String) {
        moviesList.isVisible = false
        placeholderMessage.isVisible = true
        progressBar.isVisible = false

        placeholderMessage.text = errorMessage
    }

    private fun showEmpty(errorMessage: String) {
        showError(errorMessage)
    }

    private fun showContent(movies: List<Movie>) {
        moviesList.isVisible = true
        placeholderMessage.isVisible = false
        progressBar.isVisible = false

        adapter.movies.clear()
        adapter.movies.addAll(movies)
        adapter.notifyDataSetChanged()
    }

    override fun render(state: MoviesState) {
        when(state) {
            is MoviesState.Content -> showContent(state.movies)
            is MoviesState.Empty -> showEmpty(state.message)
            is MoviesState.Error -> showError(state.errorMessage)
            MoviesState.Loading -> showLoading()
        }
    }

    override fun showToast(additionalMessage: String) {
        Toast.makeText(this, additionalMessage, Toast.LENGTH_LONG).show()
    }

    companion object {
        private const val CLICK_DEBOUNCE_DELAY = 1000L
    }

}