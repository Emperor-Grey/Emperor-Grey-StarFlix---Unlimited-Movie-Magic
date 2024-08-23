package com.king_grey.movie_app.screens.discover.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.king_grey.movie_app.core.util.constants.Constants
import com.king_grey.movie_app.screens.discover.domain.model.tvshow.TvShow

@Dao
interface TvShowDao {

    @Query("SELECT * FROM ${Constants.TV_SHOW_DATABASE_TABLE} ORDER BY id ASC")
    fun getAllTvShows(): PagingSource<Int, TvShow>

    @Query("SELECT * FROM ${Constants.TV_SHOW_DATABASE_TABLE} WHERE id = :tvShowId")
    fun getTvShowById(tvShowId: Int): TvShow

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShows(tvShows: List<TvShow>)

    @Query("DELETE FROM ${Constants.TV_SHOW_DATABASE_TABLE}")
    suspend fun deleteAllTvShows()
}
