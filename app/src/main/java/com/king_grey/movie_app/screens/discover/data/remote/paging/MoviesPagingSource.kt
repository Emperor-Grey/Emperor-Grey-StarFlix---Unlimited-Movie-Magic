package com.king_grey.movie_app.screens.discover.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.king_grey.movie_app.screens.discover.data.remote.api.TMDbService
import com.king_grey.movie_app.screens.discover.domain.model.movie.Movie

class MoviesPagingSource(
    private val apiService: TMDbService, private val apiKey: String
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition)

        return page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        return try {
            val response = apiService.getPopularMovies(apiKey, page)
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
