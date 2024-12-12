package com.practicum.moviesearchapp.domain.impl

import com.practicum.moviesearchapp.domain.api.NamesInteractor
import com.practicum.moviesearchapp.domain.api.NamesRepository
import com.practicum.moviesearchapp.util.Resource
import java.util.concurrent.Executors

class NamesInteractorImpl(private val repository: NamesRepository): NamesInteractor {

    private val executor = Executors.newCachedThreadPool()

    override fun searchNames(expression: String, consumer: NamesInteractor.NamesConsumer) {
        executor.execute{
            when(val resource = repository.searchNames(expression)) {
                is Resource.Error -> { consumer.consume(null, resource.message) }
                is Resource.Success -> { consumer.consume(resource.data, null) }
            }
        }
    }

}