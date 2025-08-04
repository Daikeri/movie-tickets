package com.example.cinema.feature.movielist.data.omdb.datasource

import com.example.cinema.core.state.ResultState
import com.example.cinema.di.OmdbApiKey
import com.example.cinema.feature.movielist.data.omdb.api.OmdbApiService
import com.example.cinema.feature.movielist.data.omdb.dto.MovieDto
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class OmdbRds @Inject constructor(
    private val apiService: OmdbApiService,
    @OmdbApiKey private val apiKey: String
) {
    suspend fun getMovieByImdbId(imdbId: String): ResultState<MovieDto> {
        return try {
            val movieDto = apiService.getMovieByImdbId(imdbId, apiKey)
            ResultState.Success(movieDto)
        } catch (e: Exception) {
            ResultState.Error(
                when {
                    e is IOException -> "Network error: ${e.message}"
                    e is HttpException -> when (e.code()) {
                        401 -> "Invalid API key"
                        404 -> "Movie not found"
                        else -> "HTTP error ${e.code()}"
                    }
                    else -> "Unexpected error: ${e.message}"
                }
            )
        }
    }
}