package com.king_grey.movie_app.screens.discover.domain.repository

import androidx.paging.Pager
import com.king_grey.movie_app.screens.discover.domain.model.movie.Movie
import com.king_grey.movie_app.screens.discover.presentation.MovieType

interface MovieRepository {
    fun fetchMovieByType(movieType: MovieType): Pager<Int, Movie>
}
