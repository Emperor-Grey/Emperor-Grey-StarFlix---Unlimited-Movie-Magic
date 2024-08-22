package com.king_grey.movie_app.screens.discover.domain.repository

import com.king_grey.movie_app.core.util.Resource
import com.king_grey.movie_app.screens.discover.domain.model.tvshow.TvShow
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {
    suspend fun getPopularTvShows(page: Int): Flow<Resource<List<TvShow>>>
    suspend fun getUpcomingTvShows(page: Int): Flow<Resource<List<TvShow>>>
    suspend fun getOnTheAirTvShows(page: Int): Flow<Resource<List<TvShow>>>
    suspend fun getAiringTodayTvShows(page: Int): Flow<Resource<List<TvShow>>>
}
