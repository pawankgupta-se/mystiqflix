package com.gowittgroup.mystiqflix.data.repositories

import com.gowittgroup.mystiqflix.data.datasource.MovieDataSource
import com.gowittgroup.mystiqflix.data.models.MovieDto
import com.gowittgroup.mystiqflix.data.util.toMovie
import com.gowittgroup.mystiqflix.domain.models.Movie
import com.gowittgroup.mystiqflix.domain.repositories.MovieRepository

internal class MovieRepositoryImpl(private val dataSource: MovieDataSource): MovieRepository {
    override suspend fun getMovies(page: Int): List<Movie> = dataSource.getMovies(page).result.map(MovieDto::toMovie)

    override suspend fun getMovie(movieId: Int): Movie = dataSource.getMovie(movieId).toMovie()
}