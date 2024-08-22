package com.king_grey.movie_app.screens.discover.data.repository

import com.king_grey.movie_app.BuildConfig
import com.king_grey.movie_app.core.util.Resource
import com.king_grey.movie_app.screens.discover.data.remote.api.TMDbService
import com.king_grey.movie_app.screens.discover.domain.model.movie.Movie
import com.king_grey.movie_app.screens.discover.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: TMDbService
) : MovieRepository {

    override suspend fun getPopularMovies(page: Int): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading())
            val movies = try {
                apiService.getPopularMovies(
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
            emit(Resource.Success(data = movies.results))
        }
    }

    override suspend fun getTopRatedMovies(page: Int): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading())
            val movies = try {
                apiService.getTopRatedMovies(
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
            emit(Resource.Success(data = movies.results))
        }
    }

    override suspend fun getUpcomingMovies(page: Int): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading())
            val movies = try {
                apiService.getUpcomingMovies(
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
            emit(Resource.Success(data = movies.results))
        }
    }

    override suspend fun getNowPlayingMovies(page: Int): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading())
            val movies = try {
                apiService.getNowPlayingMovies(
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
            emit(Resource.Success(data = movies.results))
        }
    }
}
