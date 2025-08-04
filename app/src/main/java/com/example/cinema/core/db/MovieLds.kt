package com.example.cinema.core.db

import javax.inject.Inject

class MovieLds @Inject constructor(
    private val movieDao: MovieDao
) {
    suspend fun getMovieById(imdbId: String): MovieEntity? {
        return movieDao.getMovieById(imdbId)
    }

    suspend fun insertMovie(movie: MovieEntity) {
        movieDao.insertMovie(movie)
    }

    suspend fun deleteMovie(movie: MovieEntity) {
        movieDao.deleteMovie(movie)
    }
}