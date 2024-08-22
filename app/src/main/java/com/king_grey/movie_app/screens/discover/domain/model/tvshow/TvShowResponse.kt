package com.king_grey.movie_app.screens.discover.domain.model.tvshow

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowResponse(
    val page: Int, val results: List<TvShow>, val total_pages: Int, val total_results: Int
) : Parcelable
