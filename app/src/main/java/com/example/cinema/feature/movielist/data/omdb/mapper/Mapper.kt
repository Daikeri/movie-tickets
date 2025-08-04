package com.example.cinema.feature.movielist.data.omdb.mapper

import com.example.cinema.core.db.MovieEntity
import com.example.cinema.feature.movielist.data.omdb.dto.MovieDto
import com.example.cinema.feature.movielist.domain.Movie

fun MovieDto.toDomain(): Movie {
    return Movie(
        id = imdbId,
        title = title,
        year = year.toIntOrNull() ?: 0,
        posterUrl = posterUrl,
        plot = plot,
        rating = imdbRating.toFloatOrNull() ?: 0f
    )
}

fun MovieEntity.toDomain(): Movie {
    return Movie(
        id = imdbId,
        title = title,
        year = year.toIntOrNull() ?: 0,
        posterUrl = posterUrl,
        plot = plot,
        rating = imdbRating.toFloatOrNull() ?: 0f
    )
}


fun Movie.toEntity(): MovieEntity {
    return MovieEntity(
        imdbId = id,
        title = title,
        year = year.toString(),
        posterUrl = posterUrl,
        plot = plot,
        imdbRating = rating.toString(),
    )
}
