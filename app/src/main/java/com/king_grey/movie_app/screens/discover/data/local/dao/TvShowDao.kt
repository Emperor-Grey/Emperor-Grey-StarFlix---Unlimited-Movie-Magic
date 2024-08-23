package com.king_grey.movie_app.screens.discover.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.king_grey.movie_app.core.util.constants.Constants
import com.king_grey.movie_app.screens.discover.domain.model.entities.TvShowEntity

@Dao
interface TvShowDao {

    @Query("SELECT * FROM ${Constants.TV_SHOW_DATABASE_TABLE} ORDER BY id ASC")
    fun getAllTvShows(): PagingSource<Int, TvShowEntity>

    @Query("SELECT * FROM ${Constants.TV_SHOW_DATABASE_TABLE} WHERE id = :tvShowId")
    fun getTvShowById(tvShowId: Int): TvShowEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShows(tvShows: List<TvShowEntity>)

    @Query("DELETE FROM ${Constants.TV_SHOW_DATABASE_TABLE}")
    suspend fun deleteAllTvShows()
}
