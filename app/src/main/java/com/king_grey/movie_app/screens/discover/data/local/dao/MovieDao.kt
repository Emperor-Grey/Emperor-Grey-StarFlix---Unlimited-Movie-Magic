package com.king_grey.movie_app.screens.discover.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.king_grey.movie_app.core.util.constants.Constants
import com.king_grey.movie_app.screens.discover.domain.model.entities.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM ${Constants.MOVIE_DATABASE_TABLE} ORDER BY id ASC")
    fun getAllMovies(): PagingSource<Int, MovieEntity>

    @Query("SELECT * FROM ${Constants.MOVIE_DATABASE_TABLE} WHERE id = :movieId")
    fun getMovieById(movieId: Int): MovieEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("DELETE FROM ${Constants.MOVIE_DATABASE_TABLE}")
    suspend fun deleteAllMovies()
}
