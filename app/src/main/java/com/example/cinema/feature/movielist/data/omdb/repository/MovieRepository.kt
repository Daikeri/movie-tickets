package com.example.cinema.feature.movielist.data.omdb.repository

import com.example.cinema.core.db.MovieLds
import com.example.cinema.core.state.ResultState
import com.example.cinema.feature.movielist.data.omdb.datasource.OmdbRds
import com.example.cinema.feature.movielist.data.omdb.mapper.toDomain
import com.example.cinema.feature.movielist.data.omdb.mapper.toEntity
import com.example.cinema.feature.movielist.domain.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val omdbRds: OmdbRds,
    private val movieLds: MovieLds,
) {
    private val _movieState = MutableStateFlow<ResultState<Movie>?>(null)
    val movieState: StateFlow<ResultState<Movie>?> = _movieState.asStateFlow()

    suspend fun getMovieByImdbId(imdbId: String) {
        val localMovie = movieLds.getMovieById(imdbId)
        if (localMovie != null) {
            _movieState.value = ResultState.Success(localMovie.toDomain())
            return
        }

        when (val result = omdbRds.getMovieByImdbId(imdbId)) {
            is ResultState.Success -> {
                val movie = result.data.toDomain()
                movieLds.insertMovie(movie.toEntity())
                _movieState.value = ResultState.Success(movie)
            }
            is ResultState.Error -> {
                _movieState.value = ResultState.Error(result.message)
            }
        }
    }
}