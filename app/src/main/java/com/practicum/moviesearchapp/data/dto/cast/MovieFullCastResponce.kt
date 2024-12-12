package com.practicum.moviesearchapp.data.dto.cast

import com.practicum.moviesearchapp.data.dto.Response

data class MovieFullCastResponse(
    val actors: List<ActorResponse>,
    val directors: DirectorsResponse,
    val errorMessage: String,
    val fullTitle: String,
    val imDbId: String,
    val others: List<OtherResponse>,
    val title: String,
    val type: String,
    val writers: WritersResponse,
    val year: String
) : Response()

data class ActorResponse(
    val asCharacter: String,
    val id: String,
    val image: String,
    val name: String
)

data class DirectorsResponse(
    val items: List<ItemResponse>,
    val job: String
)

data class OtherResponse(
    val items: List<ItemResponse>,
    val job: String
)

data class WritersResponse(
    val items: List<ItemResponse>,
    val job: String
)

data class ItemResponse(
    val description: String,
    val id: String,
    val name: String
)