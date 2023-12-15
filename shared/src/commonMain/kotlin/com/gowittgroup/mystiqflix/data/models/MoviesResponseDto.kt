package com.gowittgroup.mystiqflix.data.models

import kotlinx.serialization.Serializable

@Serializable
internal data class MoviesResponseDto(
    val results: List<MovieDto>
)
