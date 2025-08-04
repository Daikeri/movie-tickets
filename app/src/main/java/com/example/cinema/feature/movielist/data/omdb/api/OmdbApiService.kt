package com.example.cinema.feature.movielist.data.omdb.api

import com.example.cinema.feature.movielist.data.omdb.dto.MovieDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApiService {
    @GET("/")
    suspend fun getMovieByImdbId(
        @Query("i") imdbId: String,
        @Query("apikey") apiKey: String
    ): MovieDto
}