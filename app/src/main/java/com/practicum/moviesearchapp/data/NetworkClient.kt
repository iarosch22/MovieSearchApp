package com.practicum.moviesearchapp.data

import com.practicum.moviesearchapp.data.dto.Response

interface NetworkClient {
    fun doRequest(dto: Any): Response
    suspend fun doRequestSuspend(dto: Any): Response
}