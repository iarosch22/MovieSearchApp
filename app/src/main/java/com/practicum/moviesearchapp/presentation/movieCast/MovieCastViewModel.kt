package com.practicum.moviesearchapp.presentation.movieCast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practicum.moviesearchapp.domain.api.MoviesInteractor
import com.practicum.moviesearchapp.domain.models.MovieFullCast
import com.practicum.moviesearchapp.ui.movieCast.models.MovieCastState

class MovieCastViewModel(
    movieId: String,
    moviesInteractor: MoviesInteractor
): ViewModel() {

    private val stateLiveData = MutableLiveData<MovieCastState>()
    fun observeState(): LiveData<MovieCastState> = stateLiveData

    init {
        moviesInteractor.getMovieFullCast(movieId, object : MoviesInteractor.MovieFullCastConsumer {
            override fun consume(movieFullCast: MovieFullCast?, errorMessage: String?) {
                if (movieFullCast != null) {
                    stateLiveData.postValue(castToUiStateContent(movieFullCast))
                } else {
                    stateLiveData.postValue(errorMessage?.let { MovieCastState.Error(it) })
                }
            }
        })
    }

    private fun castToUiStateContent(cast: MovieFullCast): MovieCastState {
        // Строим список элементов RecyclerView
        val items = buildList<MoviesCastRVItem> {
            // Если есть хотя бы один режиссёр, добавим заголовок
            if (cast.directors.isNotEmpty()) {
                this += MoviesCastRVItem.HeaderItem("Directors")
                this += cast.directors.map { MoviesCastRVItem.PersonItem(it) }
            }

            // Если есть хотя бы один сценарист, добавим заголовок
            if (cast.writers.isNotEmpty()) {
                this += MoviesCastRVItem.HeaderItem("Writers")
                this += cast.writers.map { MoviesCastRVItem.PersonItem(it) }
            }

            // Если есть хотя бы один актёр, добавим заголовок
            if (cast.actors.isNotEmpty()) {
                this += MoviesCastRVItem.HeaderItem("Actors")
                this += cast.actors.map { MoviesCastRVItem.PersonItem(it) }
            }

            // Если есть хотя бы один дополнительный участник, добавим заголовок
            if (cast.others.isNotEmpty()) {
                this += MoviesCastRVItem.HeaderItem("Others")
                this += cast.others.map { MoviesCastRVItem.PersonItem(it) }
            }
        }


        return MovieCastState.Content(
            fullTitle = cast.fullTitle,
            items = items
        )
    }

}