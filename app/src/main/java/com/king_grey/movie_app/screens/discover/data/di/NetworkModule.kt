package com.king_grey.movie_app.screens.discover.data.di

import com.google.gson.GsonBuilder
import com.king_grey.movie_app.BuildConfig
import com.king_grey.movie_app.screens.discover.data.remote.api.TMDbService
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
object NetworkModule {

    @Provides
    @Singleton
    fun providesApiKey(): String = BuildConfig.API_KEY

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
}
