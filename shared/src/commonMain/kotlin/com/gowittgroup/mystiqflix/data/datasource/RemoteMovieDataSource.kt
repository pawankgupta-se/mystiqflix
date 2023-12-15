package com.gowittgroup.mystiqflix.data.datasource

import com.gowittgroup.mystiqflix.data.models.MovieDto
import com.gowittgroup.mystiqflix.data.models.MoviesResponseDto
import com.gowittgroup.mystiqflix.data.remote.MovieService
import com.gowittgroup.mystiqflix.util.Dispatcher
import kotlinx.coroutines.withContext

internal class RemoteMovieDataSource(private val service: MovieService, private val dispatcher: Dispatcher): MovieDataSource {

    override suspend fun getMovies(page: Int): MoviesResponseDto = withContext(dispatcher.io){
        service.getMovies(page)
    }

    override suspend fun getMovie(movieId: Int): MovieDto = withContext(dispatcher.io){
        service.getMovie(movieId)
    }
}