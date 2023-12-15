package com.gowittgroup.mystiqflix.android.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gowittgroup.mystiqflix.domain.models.Movie
import com.gowittgroup.mystiqflix.domain.usecases.GetMoviesUseCase
import kotlinx.coroutines.launch

data class HomeScreenState(
    val loading: Boolean = false,
    val refreshing: Boolean = false,
    val movies: List<Movie> = listOf(),
    val error: String? = null,
    val loadFinished: Boolean = false
)

class HomeViewModel(private val getMoviesUseCase: GetMoviesUseCase): ViewModel() {
    var uiState by mutableStateOf(HomeScreenState())
        private set

    private var currentPage = 1

    init {
        loadMovies(forceReload = false)
    }

    fun loadMovies(forceReload:Boolean = false){
        if (uiState.loading) return
        if(forceReload) currentPage = 1
        if(currentPage == 1) uiState = uiState.copy(refreshing = true)

        viewModelScope.launch {
            uiState = uiState.copy(loading = true)

            try {
                val result = getMoviesUseCase(currentPage)
                val tempMovies = if(currentPage == 1) result else uiState.movies + result

                currentPage++
                uiState = uiState.copy(
                    loading = false,
                    refreshing = false,
                    loadFinished = result.isEmpty(),
                    movies = tempMovies
                )
            } catch (e: Exception){
                Log.d(TAG, "Error, while loading movies: ${e.localizedMessage}")
                uiState = uiState.copy(
                    loading = false,
                    refreshing = false,
                    loadFinished = true,
                    error = "Ahh! Could not load movies"
                )
            }



        }
    }

    companion object {
        private val TAG = HomeViewModel::class.java.simpleName
    }
}