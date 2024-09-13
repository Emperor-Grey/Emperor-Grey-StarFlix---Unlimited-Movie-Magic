package com.king_grey.movie_app.core.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.king_grey.movie_app.core.ui.navigation.BottomNav
import com.king_grey.movie_app.core.ui.navigation.Screen
import com.king_grey.movie_app.core.ui.navigation.SetUpNavigation
import com.king_grey.movie_app.core.ui.theme.MovieAppTheme
import com.king_grey.movie_app.main.presentation.screens.splash.SplashScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private lateinit var splashViewModel: SplashScreenViewModel

    @OptIn(ExperimentalSharedTransitionApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition {
            !splashViewModel.isLoading.value
        }

        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.auto(
                android.graphics.Color.TRANSPARENT, android.graphics.Color.TRANSPARENT
            ), statusBarStyle = SystemBarStyle.auto(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT,
            )
        )
        setContent {
            MovieAppTheme {
                navController = rememberNavController()
                splashViewModel = hiltViewModel()

                val startDestination = splashViewModel.startDestination.collectAsState()
                val isLoading = splashViewModel.isLoading.collectAsState()

                Scaffold(bottomBar = {
                    val showBottomBar = startDestination.value in listOf(
                        Screen.Home.route,
                        Screen.Discover.route,
                        Screen.MyList.route,
                        Screen.Settings.route
                    ) && navController.currentBackStackEntryAsState().value?.destination?.route in listOf(
                        Screen.Home.route,
                        Screen.Discover.route,
                        Screen.MyList.route,
                        Screen.Settings.route
                    )

                    if (showBottomBar) {
                        BottomNav(navController = navController)
                    }
                }) { padding ->
                    if (isLoading.value) {
                        CircularProgressIndicator()
                    } else {
                        SetUpNavigation(
                            navController = navController,
                            startDestination = startDestination.value,
                            padding = padding,
                        )
                    }
                }
            }
        }
    }
}
