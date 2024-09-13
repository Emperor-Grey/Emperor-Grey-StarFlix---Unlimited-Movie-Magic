package com.king_grey.movie_app.main.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.king_grey.movie_app.core.ui.navigation.Screen
import com.king_grey.movie_app.core.util.preferences.DataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val dataStore: DataStore
) : ViewModel() {

    private val _isSplashComplete = MutableStateFlow(false)
    val isSplashComplete = _isSplashComplete.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _startDestination = MutableStateFlow(Screen.Splash.route)
    val startDestination = _startDestination.asStateFlow()

    init {
        viewModelScope.launch {
            dataStore.readOnBoardingState().collect { complete ->
                _isSplashComplete.value = complete
                _startDestination.value = if (complete) Screen.Home.route else Screen.Splash.route
                _isLoading.value = false
            }
        }
    }

    fun setIsSplashComplete(completed: Boolean) {
        viewModelScope.launch {
            dataStore.saveOnBoardingState(completed)
        }
    }
}
