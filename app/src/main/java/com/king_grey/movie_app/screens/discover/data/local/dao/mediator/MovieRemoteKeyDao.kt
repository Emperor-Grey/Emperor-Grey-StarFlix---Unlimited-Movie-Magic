package com.king_grey.movie_app.screens.discover.data.local.dao.mediator

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.king_grey.movie_app.core.util.constants.Constants
import com.king_grey.movie_app.screens.discover.domain.model.movie.MovieRemoteKeys

@Dao
interface MovieRemoteKeyDao {

    @Query("SELECT * FROM ${Constants.MOVIE_REMOTE_KEYS_DATABASE_TABLE} WHERE id = :movieId")
    suspend fun getRemoteKeys(movieId: Int): MovieRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(movieRemoteKeys: List<MovieRemoteKeys>)

    @Query("DELETE FROM ${Constants.MOVIE_REMOTE_KEYS_DATABASE_TABLE}")
    suspend fun deleteAllRemoteKeys()
}
