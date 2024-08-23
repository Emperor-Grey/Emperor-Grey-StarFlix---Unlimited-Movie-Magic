package com.king_grey.movie_app.screens.discover.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.king_grey.movie_app.screens.discover.domain.model.movie.Movie

class MoviesPagingSource : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        TODO("Not yet implemented")
    }

}
