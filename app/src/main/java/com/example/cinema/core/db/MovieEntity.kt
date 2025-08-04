package com.example.cinema.core.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val imdbId: String,
    val title: String,
    val year: String,
    val posterUrl: String,
    val plot: String,
    val imdbRating: String,
)