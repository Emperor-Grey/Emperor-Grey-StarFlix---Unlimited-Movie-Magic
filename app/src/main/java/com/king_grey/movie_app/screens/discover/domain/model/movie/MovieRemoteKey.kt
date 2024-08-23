package com.king_grey.movie_app.screens.discover.domain.model.movie

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.king_grey.movie_app.core.util.constants.Constants

@Entity(tableName = Constants.MOVIE_REMOTE_KEY_DATABASE_TABLE)
data class MovieRemoteKey(
    @PrimaryKey(autoGenerate = false) val id: Int, val prevPage: Int?, val nextPage: Int?
)
