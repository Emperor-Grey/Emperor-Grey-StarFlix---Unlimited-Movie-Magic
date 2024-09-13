package com.king_grey.movie_app.core.util.constants

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesConstants {
    val MOVIE_TYPE_KEY = stringPreferencesKey("movie_type")
    val TV_SHOW_TYPE_KEY = stringPreferencesKey("tv_show_type")
    val ONBOARDING_KEY = booleanPreferencesKey("onboarding")
}
