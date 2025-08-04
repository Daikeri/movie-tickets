package com.example.cinema.feature.movielist.domain

data class Movie(
    val id: String,
    val title: String,
    val year: Int,
    val posterUrl: String,
    val plot: String,
    val rating: Float,
)