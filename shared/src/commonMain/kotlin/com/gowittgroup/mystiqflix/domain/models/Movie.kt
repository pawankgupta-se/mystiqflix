package com.gowittgroup.mystiqflix.domain.models


data class Movie(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
    val releaseDate: String
)
