package com.gowittgroup.mystiqflix.data.datasource

import com.gowittgroup.mystiqflix.data.models.MovieDto
import com.gowittgroup.mystiqflix.data.models.MoviesResponseDto

internal interface MovieDataSource {
    suspend fun getMovies(page: Int): MoviesResponseDto
    suspend fun getMovie(movieId: Int): MovieDto
}