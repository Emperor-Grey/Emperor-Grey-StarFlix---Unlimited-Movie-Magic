package com.king_grey.movie_app.screens.discover.data.di

import com.king_grey.movie_app.screens.discover.data.remote.api.TMDbService
import com.king_grey.movie_app.screens.discover.data.repository.MovieRepositoryImpl
import com.king_grey.movie_app.screens.discover.data.repository.TvShowRepositoryImpl
import com.king_grey.movie_app.screens.discover.domain.repository.MovieRepository
import com.king_grey.movie_app.screens.discover.domain.repository.TvShowRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiscoverModule {
    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(TMDbService.BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())).build()
    }

    @Provides
    @Singleton
    fun providesTMDbApi(retrofit: Retrofit): TMDbService {
        return retrofit.create(TMDbService::class.java)
    }

    @Provides
    @Singleton
    fun providesMovieRepository(apiService: TMDbService): MovieRepository {
        return MovieRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun providesTvShowRepository(apiService: TMDbService): TvShowRepository {
        return TvShowRepositoryImpl(apiService)
    }
}
