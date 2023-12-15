package com.gowittgroup.mystiqflix.domain.repositories

import com.gowittgroup.mystiqflix.domain.models.Movie

internal interface MovieRepository {
    suspend fun getMovies(page: Int): List<Movie>
    suspend fun getMovie(movieId: Int): Movie
}