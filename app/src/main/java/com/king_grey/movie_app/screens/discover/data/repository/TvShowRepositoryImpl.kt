package com.king_grey.movie_app.screens.discover.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.king_grey.movie_app.screens.discover.data.remote.api.TMDbService
import com.king_grey.movie_app.screens.discover.data.remote.paging.TvShowPagingSource
import com.king_grey.movie_app.screens.discover.domain.model.tvshow.TvShow
import com.king_grey.movie_app.screens.discover.domain.repository.TvShowRepository
import com.king_grey.movie_app.screens.discover.presentation.TvShowType
import javax.inject.Inject

class TvShowRepositoryImpl @Inject constructor(
    private val apiService: TMDbService, private val apiKey: String
) : TvShowRepository {

    override fun fetchTvShowByType(tvShowType: TvShowType): Pager<Int, TvShow> {
        return Pager(config = PagingConfig(
            pageSize = 20, enablePlaceholders = false, prefetchDistance = 3
        ), pagingSourceFactory = { TvShowPagingSource(apiService, apiKey, tvShowType) })
    }
}
