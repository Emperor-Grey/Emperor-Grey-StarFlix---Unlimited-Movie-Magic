package com.king_grey.movie_app.screens.discover.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.king_grey.movie_app.core.util.Resource
import com.king_grey.movie_app.screens.discover.data.remote.api.TMDbService
import com.king_grey.movie_app.screens.discover.data.remote.paging.MoviesPagingSource
import com.king_grey.movie_app.screens.discover.domain.model.movie.Movie
import com.king_grey.movie_app.screens.discover.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: TMDbService, private val apiKey: String
) : MovieRepository {

    override fun fetchPopularMovies(): Pager<Int, Movie> {
        return Pager(config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { MoviesPagingSource(apiService, apiKey) })
    }

    override suspend fun getPopularMovies(page: Int): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading())
            val movies = try {
                apiService.getPopularMovies(
                    page = page, apiKey = apiKey
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
                    page = page, apiKey = apiKey
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
                    page = page, apiKey = apiKey
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
                    page = page, apiKey = apiKey
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
