package com.king_grey.movie_app.screens.discover.domain.repository

import androidx.paging.Pager
import com.king_grey.movie_app.screens.discover.domain.model.tvshow.TvShow
import com.king_grey.movie_app.screens.discover.presentation.TvShowType

interface TvShowRepository {
    fun fetchTvShowByType(tvShowType: TvShowType): Pager<Int, TvShow>
}
