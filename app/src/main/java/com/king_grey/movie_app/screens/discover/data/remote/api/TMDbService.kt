package com.king_grey.movie_app.screens.discover.data.remote.api

import com.king_grey.movie_app.screens.discover.domain.model.movie.MovieResponse
import com.king_grey.movie_app.screens.discover.domain.model.tvshow.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDbService {

    /* For Movies */

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
    ): MovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
    ): MovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
    ): MovieResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
    ): MovieResponse

    /* For Tv shows */

    @GET("tv/airing_today")
    suspend fun getAiringTodayTvShows(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
    ): TvShowResponse

    @GET("tv/on_the_air")
    suspend fun getOnTheAirTvShows(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
    ): TvShowResponse

    @GET("tv/popular")
    suspend fun getPopularTvShows(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
    ): TvShowResponse

    @GET("tv/top_rated")
    suspend fun getUpcomingTvShows(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
    ): TvShowResponse

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original/"
    }
}
