package com.king_grey.movie_app.screens.discover.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.king_grey.movie_app.core.util.preferences.DataStore
import com.king_grey.movie_app.screens.discover.domain.model.movie.Movie
import com.king_grey.movie_app.screens.discover.domain.repository.MovieRepository
import com.king_grey.movie_app.screens.discover.domain.repository.TvShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class DiscoverViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val tvShowRepository: TvShowRepository,
    private val dataStore: DataStore
) : ViewModel() {

    private val _selectedTabIndex = MutableStateFlow(0)
    val selectedTabIndex: StateFlow<Int> = _selectedTabIndex

    private val _movieType = MutableStateFlow(MovieType.Popular)
    val movieType = _movieType.asStateFlow()

    private val _tvShowType = MutableStateFlow(TvShowType.PopularTv)
    val tvShowType = _tvShowType.asStateFlow()

    init {
        viewModelScope.launch {
            dataStore.readMovieType().collect { movieType ->
                _movieType.value = movieType
            }
            dataStore.readTvShowType().collect { tvShowType ->
                _tvShowType.value = tvShowType
            }
        }
    }


    val pagedMovies: Flow<PagingData<Movie>> = _movieType.flatMapLatest { type ->
        movieRepository.fetchMovieByType(type).flow
    }.cachedIn(viewModelScope)


    val pagedTvShows = _tvShowType.flatMapLatest { type ->
        tvShowRepository.fetchTvShowByType(type).flow
    }.cachedIn(viewModelScope)


    fun setSelectedTabIndex(index: Int) {
        _selectedTabIndex.value = index
    }

    fun setMovieType(movieType: MovieType) {
        _movieType.value = movieType
        viewModelScope.launch {
            dataStore.saveMovieType(movieType)
        }
    }

    fun setTvShowType(tvShowType: TvShowType) {
        _tvShowType.value = tvShowType
        viewModelScope.launch {
            dataStore.saveTvShowType(tvShowType)
        }
    }
}


enum class MovieType {
    Popular, Upcoming, TopRated, NowPlaying,
}

enum class TvShowType {
    PopularTv, UpcomingTv, OnTheAirTv, AiringTodayTv,
}
