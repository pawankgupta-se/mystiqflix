package com.gowittgroup.mystiqflix.android.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gowittgroup.mystiqflix.domain.models.Movie
import com.gowittgroup.mystiqflix.domain.usecases.GetMovieByIdUseCase
import kotlinx.coroutines.launch


data class DetailScreenState(
    val isLoading: Boolean = false,
    val movie: Movie? = null,
    val error: String? = null
)
class DetailViewModel(
    private val getMovieByIdUseCase: GetMovieByIdUseCase,
    private val movieId: Int
) : ViewModel(){
    var uiState by mutableStateOf(DetailScreenState())
        private set

    init {
        loadMovie(movieId)
    }
    private fun loadMovie(movieId: Int){
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)

            uiState = try{
                val movie = getMovieByIdUseCase(movieId)
                uiState.copy(
                    isLoading = false,
                    movie = movie
                )

            }catch (e:Exception){

                uiState.copy(
                    isLoading = false,
                    error = "Ahh, could not load movie detail"
                    )
            }
        }
    }
}

