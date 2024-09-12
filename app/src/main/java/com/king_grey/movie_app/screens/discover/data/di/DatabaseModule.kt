package com.king_grey.movie_app.screens.discover.data.di

import android.content.Context
import androidx.room.Room
import com.king_grey.movie_app.core.util.constants.Constants
import com.king_grey.movie_app.screens.discover.data.local.TMDbDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): TMDbDatabase {
        return Room.databaseBuilder(
            context, TMDbDatabase::class.java, Constants.TMDB_DATABASE
        ).build()
    }
}
