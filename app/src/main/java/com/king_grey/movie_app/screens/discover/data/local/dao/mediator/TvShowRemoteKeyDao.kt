package com.king_grey.movie_app.screens.discover.data.local.dao.mediator

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.king_grey.movie_app.core.util.constants.Constants
import com.king_grey.movie_app.screens.discover.domain.model.tvshow.TvShowRemoteKey

@Dao
interface TvShowRemoteKeyDao {

    @Query("SELECT * FROM ${Constants.TV_SHOW_REMOTE_KEY_DATABASE_TABLE} WHERE id = :tvShowId")
    suspend fun getRemoteKey(tvShowId: Int): TvShowRemoteKey?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(tvShowRemoteKeys: List<TvShowRemoteKey>)

    @Query("DELETE FROM ${Constants.TV_SHOW_REMOTE_KEY_DATABASE_TABLE}")
    suspend fun deleteAllRemoteKeys()

}
