package com.king_grey.movie_app.screens.discover.domain.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.king_grey.movie_app.core.util.constants.Constants

@Entity(tableName = Constants.TV_SHOW_DATABASE_TABLE)
data class TvShowEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,


    val adult: Boolean,
    val backdrop_path: String,
    val first_air_date: String,
    val genre_ids: List<Int>,
    val media_type: String,
    val name: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int
)
