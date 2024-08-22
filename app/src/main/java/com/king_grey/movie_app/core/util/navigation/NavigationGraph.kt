package com.king_grey.movie_app.core.util.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.king_grey.movie_app.main.presentation.screens.splash.SplashScreen
import com.king_grey.movie_app.screens.discover.presentation.DiscoverScreen
import com.king_grey.movie_app.screens.home.presentation.HomeScreen


@Composable
fun SetUpNavigation(
    navController: NavHostController, startDestination: String, padding: PaddingValues
) {

    NavHost(navController = navController, startDestination) {

        composable(route = Screen.Splash.route) {
            SplashScreen(navController)
        }

        composable(route = Screen.Home.route) {
            HomeScreen(navController, padding)
        }

        composable(route = Screen.Discover.route) {
            DiscoverScreen(navController)
        }

        composable(route = Screen.MyList.route) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "My List")
                }
            }
        }

        composable(route = Screen.Settings.route) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(text = "Settings")
                }
            }
        }

        composable(route = Screen.MovieDetails.route) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(text = "Details")
                }
            }
        }
    }
}
