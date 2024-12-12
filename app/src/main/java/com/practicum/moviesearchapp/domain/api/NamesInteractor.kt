package com.practicum.moviesearchapp.domain.api

import com.practicum.moviesearchapp.domain.models.Name

interface NamesInteractor {

    fun searchNames(expression: String, consumer: NamesConsumer)

    interface NamesConsumer {
        fun consume(foundNames: List<Name>?, errorMessage: String?)
    }

}