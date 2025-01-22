package com.practicum.moviesearchapp.data.converters

import com.practicum.moviesearchapp.data.db.entity.MovieEntity
import com.practicum.moviesearchapp.data.dto.movies.MovieDto
import com.practicum.moviesearchapp.domain.models.Movie

class MovieDbConvertor {

    fun map(movie: MovieDto): MovieEntity {
        return MovieEntity(movie.id, movie.resultType, movie.image, movie.title, movie.description)
    }

    fun map(movie: MovieEntity): Movie {
        return Movie(movie.id, movie.resultType, movie.image, movie.title, movie.description, false)
    }

}