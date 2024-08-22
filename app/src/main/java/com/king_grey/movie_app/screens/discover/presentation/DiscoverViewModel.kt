package com.king_grey.movie_app.screens.discover.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.king_grey.movie_app.core.util.Resource
import com.king_grey.movie_app.screens.discover.domain.model.movie.Movie
import com.king_grey.movie_app.screens.discover.domain.model.tvshow.TvShow
import com.king_grey.movie_app.screens.discover.domain.repository.MovieRepository
import com.king_grey.movie_app.screens.discover.domain.repository.TvShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val tvShowRepository: TvShowRepository,
) : ViewModel() {

    // Movies State
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies = _movies.asStateFlow()

    private val _movieLoading = MutableStateFlow(false)
    val movieLoading = _movieLoading.asStateFlow()

    // TV Shows State
    private val _tvShows = MutableStateFlow<List<TvShow>>(emptyList())
    val tvShows = _tvShows.asStateFlow()

    private val _tvShowLoading = MutableStateFlow(false)
    val tvShowLoading = _tvShowLoading.asStateFlow()

    private var currentPage: Int = 1

    fun fetchMovies(movieType: MovieType) {
        viewModelScope.launch {
            val flow = when (movieType) {
                MovieType.Popular -> movieRepository.getPopularMovies(currentPage)
                MovieType.Upcoming -> movieRepository.getUpcomingMovies(currentPage)
                MovieType.TopRated -> movieRepository.getTopRatedMovies(currentPage)
                MovieType.NowPlaying -> movieRepository.getNowPlayingMovies(currentPage)
            }

            _movieLoading.value = true
            flow.collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _movies.value = resource.data ?: emptyList()
                        _movieLoading.value = false
                    }

                    is Resource.Error -> {
                        _movieLoading.value = false
                    }

                    is Resource.Loading -> {
                        _movieLoading.value = true
                    }
                }
            }
        }
    }

    fun fetchTvShows(tvShowType: TvShowType) {
        viewModelScope.launch {
            val flow = when (tvShowType) {
                TvShowType.PopularTv -> tvShowRepository.getPopularTvShows(currentPage)
                TvShowType.UpcomingTv -> tvShowRepository.getUpcomingTvShows(currentPage)
                TvShowType.OnTheAirTv -> tvShowRepository.getOnTheAirTvShows(currentPage)
                TvShowType.AiringTodayTv -> tvShowRepository.getAiringTodayTvShows(currentPage)
            }

            _tvShowLoading.value = true
            flow.collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _tvShows.value = resource.data ?: emptyList()
                        _tvShowLoading.value = false
                    }

                    is Resource.Error -> {
                        _tvShowLoading.value = false
                    }

                    is Resource.Loading -> {
                        _tvShowLoading.value = true
                    }
                }
            }
        }
    }
}


enum class MovieType {
    TopRated, Popular, Upcoming, NowPlaying,
}

enum class TvShowType {
    PopularTv, OnTheAirTv, AiringTodayTv, UpcomingTv,
}
