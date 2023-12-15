package com.gowittgroup.mystiqflix.domain.usecases

import com.gowittgroup.mystiqflix.domain.models.Movie
import com.gowittgroup.mystiqflix.domain.repositories.MovieRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetMovieByIdUseCase: KoinComponent {
    private val repository: MovieRepository by inject()

    @Throws(Exception::class)
    suspend operator fun invoke(movieId: Int): Movie = repository.getMovie(movieId)

}