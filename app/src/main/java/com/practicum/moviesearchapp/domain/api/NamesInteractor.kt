package com.practicum.moviesearchapp.domain.api

import com.practicum.moviesearchapp.domain.models.Person

interface NamesInteractor {

    fun searchNames(expression: String, consumer: NamesConsumer)

    interface NamesConsumer {
        fun consume(foundPersons: List<Person>?, errorMessage: String?)
    }

}