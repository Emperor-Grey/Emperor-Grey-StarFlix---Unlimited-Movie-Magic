package com.king_grey.movie_app.screens.discover.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.king_grey.movie_app.screens.discover.data.remote.api.TMDbService
import com.king_grey.movie_app.screens.discover.domain.model.tvshow.TvShow
import com.king_grey.movie_app.screens.discover.presentation.TvShowType

class TvShowPagingSource(
    private val apiService: TMDbService,
    private val apiKey: String,
    private val tvShowType: TvShowType
) : PagingSource<Int, TvShow>() {

    override fun getRefreshKey(state: PagingState<Int, TvShow>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition)

        return page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvShow> {
        val page = params.key ?: 1
        return try {
            val response = when (tvShowType) {
                TvShowType.PopularTv -> apiService.getPopularTvShows(apiKey, page)
                TvShowType.OnTheAirTv -> apiService.getOnTheAirTvShows(apiKey, page)
                TvShowType.UpcomingTv -> apiService.getUpcomingTvShows(apiKey, page)
                TvShowType.AiringTodayTv -> apiService.getAiringTodayTvShows(apiKey, page)
            }

            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = if (page < response.total_pages) page + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
