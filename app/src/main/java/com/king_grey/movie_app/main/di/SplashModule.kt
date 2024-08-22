package com.king_grey.movie_app.main.di

import android.content.Context
import com.king_grey.movie_app.core.util.preferences.DataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SplashModule {

    @Provides
    @Singleton
    fun providesDataStore(@ApplicationContext context: Context): DataStore {
        return DataStore(context)
    }
}
