package com.king_grey.movie_app.core.util.preferences

import android.content.Context
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.king_grey.movie_app.core.util.constants.PreferencesConstants
import com.king_grey.movie_app.screens.discover.presentation.MovieType
import com.king_grey.movie_app.screens.discover.presentation.TvShowType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


class DataStore(private val context: Context) {
    private val dataStore = context.dataStore

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesConstants.ONBOARDING_KEY] = completed
        }
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val onBoardingState = preferences[PreferencesConstants.ONBOARDING_KEY] ?: false
            onBoardingState
        }
    }

    suspend fun saveMovieType(movieType: MovieType) {
        dataStore.edit { preferences ->
            preferences[PreferencesConstants.MOVIE_TYPE_KEY] = movieType.name
        }
    }

    fun readMovieType(): Flow<MovieType> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val movieType =
                preferences[PreferencesConstants.MOVIE_TYPE_KEY] ?: MovieType.Popular.name
            MovieType.valueOf(movieType)
        }
    }

    suspend fun saveTvShowType(tvShowType: TvShowType) {
        dataStore.edit { preferences ->
            preferences[PreferencesConstants.TV_SHOW_TYPE_KEY] = tvShowType.name
        }
    }

    fun readTvShowType(): Flow<TvShowType> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val tvShowType =
                preferences[PreferencesConstants.TV_SHOW_TYPE_KEY] ?: TvShowType.PopularTv.name
            TvShowType.valueOf(tvShowType)
        }
    }
}
