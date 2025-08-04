package com.example.cinema.feature.movielist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.core.state.ResultState
import com.example.cinema.feature.movielist.data.omdb.repository.MovieRepository
import com.example.cinema.feature.movielist.domain.Movie
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)

    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        movieRepository.movieState
            .onEach { resultState ->
                _uiState.value = when (resultState) {
                    is ResultState.Success -> {
                        UiState.ShowData(resultState.data)
                    }
                    is ResultState.Error -> {
                        UiState.ShowError(resultState.message)
                    }
                    null -> UiState.Loading
                }
            }
            .launchIn(viewModelScope)
    }

    fun getMovieByImdbId(imdbId: String) {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            movieRepository.getMovieByImdbId(imdbId)
        }
    }
}

sealed class UiState {
    data class ShowData(val movie: Movie) : UiState()
    data class ShowError(val message: String) : UiState()
    object Loading : UiState()
}