package com.king_grey.movie_app.screens.discover.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.king_grey.movie_app.screens.discover.data.remote.api.TMDbService
import com.king_grey.movie_app.screens.discover.domain.model.movie.Movie
import com.king_grey.movie_app.screens.discover.presentation.MovieType

class MoviesPagingSource(
    private val apiService: TMDbService,
    private val apiKey: String,
    private val movieType: MovieType,
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition)

        return page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        return try {
            val response = when (movieType) {
                MovieType.TopRated -> apiService.getTopRatedMovies(apiKey, page)
                MovieType.Popular -> apiService.getPopularMovies(apiKey, page)
                MovieType.Upcoming -> apiService.getUpcomingMovies(apiKey, page)
                MovieType.NowPlaying -> apiService.getNowPlayingMovies(apiKey, page)
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
