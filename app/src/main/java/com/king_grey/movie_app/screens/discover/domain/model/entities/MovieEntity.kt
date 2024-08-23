package com.king_grey.movie_app.screens.discover.domain.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.king_grey.movie_app.core.util.constants.Constants


@Entity(tableName = Constants.MOVIE_DATABASE_TABLE)
data class MovieEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,

    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val media_type: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)
