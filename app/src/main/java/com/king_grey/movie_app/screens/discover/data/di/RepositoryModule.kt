package com.king_grey.movie_app.screens.discover.data.di

import com.king_grey.movie_app.screens.discover.data.repository.MovieRepositoryImpl
import com.king_grey.movie_app.screens.discover.data.repository.TvShowRepositoryImpl
import com.king_grey.movie_app.screens.discover.domain.repository.MovieRepository
import com.king_grey.movie_app.screens.discover.domain.repository.TvShowRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository

    @Binds
    @Singleton
    fun bindTvShowRepository(
        tvShowRepositoryImpl: TvShowRepositoryImpl
    ): TvShowRepository
}
