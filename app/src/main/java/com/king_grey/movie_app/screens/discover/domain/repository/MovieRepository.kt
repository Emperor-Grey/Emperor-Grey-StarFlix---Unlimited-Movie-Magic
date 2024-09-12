package com.king_grey.movie_app.screens.discover.domain.repository

import androidx.paging.Pager
import com.king_grey.movie_app.core.util.Resource
import com.king_grey.movie_app.screens.discover.domain.model.movie.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getPopularMovies(page: Int): Flow<Resource<List<Movie>>>
    suspend fun getTopRatedMovies(page: Int): Flow<Resource<List<Movie>>>
    suspend fun getUpcomingMovies(page: Int): Flow<Resource<List<Movie>>>
    suspend fun getNowPlayingMovies(page: Int): Flow<Resource<List<Movie>>>

    fun fetchPopularMovies(): Pager<Int, Movie>
}
