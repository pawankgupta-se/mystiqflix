package com.gowittgroup.mystiqflix.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MovieDto(
    val id:Int,
    val title: String,
    val overview: String,
    val posterImage: String,

    @SerialName("release_date")
    val releaseDate: String
)
