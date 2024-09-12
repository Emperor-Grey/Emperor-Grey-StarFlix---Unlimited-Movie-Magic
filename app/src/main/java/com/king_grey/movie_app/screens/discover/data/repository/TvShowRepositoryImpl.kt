package com.king_grey.movie_app.screens.discover.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.king_grey.movie_app.BuildConfig
import com.king_grey.movie_app.core.util.Resource
import com.king_grey.movie_app.screens.discover.data.remote.api.TMDbService
import com.king_grey.movie_app.screens.discover.data.remote.paging.TvShowPagingSource
import com.king_grey.movie_app.screens.discover.domain.model.tvshow.TvShow
import com.king_grey.movie_app.screens.discover.domain.repository.TvShowRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class TvShowRepositoryImpl @Inject constructor(
    private val apiService: TMDbService, private val apiKey: String
) : TvShowRepository {

    override fun fetchPopularTvShows(): Pager<Int, TvShow> {
        return Pager(config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { TvShowPagingSource(apiService, apiKey) })
    }


    override suspend fun getPopularTvShows(page: Int): Flow<Resource<List<TvShow>>> {
        return flow {
            emit(Resource.Loading())
            val tvShows = try {
                apiService.getPopularTvShows(
                    page = page, apiKey = BuildConfig.API_KEY
                )
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error fetching popular movies"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error fetching popular movies"))
                return@flow
            }
            emit(Resource.Success(data = tvShows.results))
        }
    }

    override suspend fun getUpcomingTvShows(page: Int): Flow<Resource<List<TvShow>>> {
        return flow {
            emit(Resource.Loading())
            val tvShows = try {
                apiService.getUpcomingTvShows(
                    page = page, apiKey = BuildConfig.API_KEY
                )
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error fetching popular movies"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error fetching popular movies"))
                return@flow
            }
            emit(Resource.Success(data = tvShows.results))
        }
    }

    override suspend fun getOnTheAirTvShows(page: Int): Flow<Resource<List<TvShow>>> {
        return flow {
            emit(Resource.Loading())
            val tvShows = try {
                apiService.getOnTheAirTvShows(
                    page = page, apiKey = BuildConfig.API_KEY
                )
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error fetching popular movies"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error fetching popular movies"))
                return@flow
            }
            emit(Resource.Success(data = tvShows.results))
        }
    }

    override suspend fun getAiringTodayTvShows(page: Int): Flow<Resource<List<TvShow>>> {
        return flow {
            emit(Resource.Loading())
            val tvShows = try {
                apiService.getAiringTodayTvShows(
                    page = page, apiKey = BuildConfig.API_KEY
                )
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error fetching popular movies"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error fetching popular movies"))
                return@flow
            }
            emit(Resource.Success(data = tvShows.results))
        }
    }
}
