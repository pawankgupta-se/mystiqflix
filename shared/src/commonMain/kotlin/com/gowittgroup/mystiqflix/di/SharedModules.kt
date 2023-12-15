package com.gowittgroup.mystiqflix.di

import com.gowittgroup.mystiqflix.data.datasource.MovieDataSource
import com.gowittgroup.mystiqflix.data.datasource.RemoteMovieDataSource
import com.gowittgroup.mystiqflix.data.remote.MovieService
import com.gowittgroup.mystiqflix.data.repositories.MovieRepositoryImpl
import com.gowittgroup.mystiqflix.domain.repositories.MovieRepository
import com.gowittgroup.mystiqflix.domain.usecases.GetMovieByIdUseCase
import com.gowittgroup.mystiqflix.domain.usecases.GetMoviesUseCase
import com.gowittgroup.mystiqflix.util.provideDispatcher
import org.koin.dsl.module

private val dataModule = module {
    factory { MovieService() }
    factory<MovieDataSource> { RemoteMovieDataSource(get(), get()) }
}

private val utilityModule = module {
    factory { provideDispatcher() }
}

private val domainModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }
    factory { GetMoviesUseCase() }
    factory { GetMovieByIdUseCase() }
}


private val sharedModules = listOf(dataModule, utilityModule, domainModule)
fun getSharedModules() = sharedModules