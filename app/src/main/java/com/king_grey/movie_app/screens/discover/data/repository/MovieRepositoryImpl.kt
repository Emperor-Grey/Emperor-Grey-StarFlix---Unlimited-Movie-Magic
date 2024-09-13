package com.king_grey.movie_app.screens.discover.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.king_grey.movie_app.screens.discover.data.remote.api.TMDbService
import com.king_grey.movie_app.screens.discover.data.remote.paging.MoviesPagingSource
import com.king_grey.movie_app.screens.discover.domain.model.movie.Movie
import com.king_grey.movie_app.screens.discover.domain.repository.MovieRepository
import com.king_grey.movie_app.screens.discover.presentation.MovieType
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: TMDbService, private val apiKey: String
) : MovieRepository {

    override fun fetchMovieByType(movieType: MovieType): Pager<Int, Movie> {
        return Pager(config = PagingConfig(
            pageSize = 20, enablePlaceholders = false, prefetchDistance = 3
        ), pagingSourceFactory = { MoviesPagingSource(apiService, apiKey, movieType) })
    }
}
