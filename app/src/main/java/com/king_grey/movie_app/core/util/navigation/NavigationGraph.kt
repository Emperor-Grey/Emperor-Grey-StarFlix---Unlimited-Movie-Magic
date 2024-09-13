package com.king_grey.movie_app.core.util.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.king_grey.movie_app.main.presentation.screens.splash.SplashScreen
import com.king_grey.movie_app.screens.discover.presentation.DiscoverScreen
import com.king_grey.movie_app.screens.home.presentation.HomeScreen
import com.king_grey.movie_app.screens.movie_details.presentation.MovieDetailsScreen
import com.king_grey.movie_app.screens.mylist.presentation.MyListScreen
import com.king_grey.movie_app.screens.settings.presentation.SettingsScreen


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
            DiscoverScreen(navController, padding)
        }

        composable(route = Screen.MyList.route) {
            MyListScreen(navController, padding)
        }

        composable(route = Screen.Settings.route) {
            SettingsScreen(navController, padding)
        }

        composable(route = Screen.MovieDetails.route) {
            MovieDetailsScreen(navController, padding)
        }
    }
}
