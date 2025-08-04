package com.example.cinema.feature.movielist.data.omdb.dto

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("imdbID") val imdbId: String,
    @SerializedName("Type") val type: String,
    @SerializedName("Poster") val posterUrl: String,
    @SerializedName("Plot") val plot: String,
    @SerializedName("imdbRating") val imdbRating: String
)