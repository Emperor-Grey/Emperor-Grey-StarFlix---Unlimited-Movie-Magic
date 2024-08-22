package com.king_grey.movie_app.screens.discover.domain.model.movie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(
    val page: Int, val results: List<Movie>, val total_pages: Int, val total_results: Int
) : Parcelable
