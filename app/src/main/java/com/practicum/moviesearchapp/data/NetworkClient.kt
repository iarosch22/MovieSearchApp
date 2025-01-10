package com.practicum.moviesearchapp.data

import com.practicum.moviesearchapp.data.dto.Response

interface NetworkClient {

    suspend fun doRequestSuspend(dto: Any): Response

}