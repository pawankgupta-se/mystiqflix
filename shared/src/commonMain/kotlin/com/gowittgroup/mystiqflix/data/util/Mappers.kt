package com.gowittgroup.mystiqflix.data.util

import com.gowittgroup.mystiqflix.data.models.MovieDto
import com.gowittgroup.mystiqflix.domain.models.Movie

internal fun MovieDto.toMovie():Movie = Movie(
        id = id,
        title  = title,
        description = overview,
        imageUrl = getImageUrl(posterImage),
        releaseDate = releaseDate
    )
private fun getImageUrl(posterImage: String) = "https://image.tmdb.org/t/p/w500/$posterImage"