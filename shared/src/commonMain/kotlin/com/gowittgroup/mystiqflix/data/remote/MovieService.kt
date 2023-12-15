package com.gowittgroup.mystiqflix.data.remote

import com.gowittgroup.mystiqflix.data.models.MovieDto
import com.gowittgroup.mystiqflix.data.models.MoviesResponseDto
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal class MovieService: KtorApi() {
    suspend fun getMovies(page: Int): MoviesResponseDto = client.get {
        pathUrl("movie/popular")
        parameter("page", page)
    }.body()

    suspend fun getMovie(movieId: Int): MovieDto = client.get {
        pathUrl("movie/${movieId}")
    }.body()
}