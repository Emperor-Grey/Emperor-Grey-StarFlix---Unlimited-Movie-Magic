package com.king_grey.movie_app.core.ui.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.king_grey.movie_app.main.presentation.screens.splash.SplashScreen
import com.king_grey.movie_app.screens.discover.domain.model.movie.Movie
import com.king_grey.movie_app.screens.discover.domain.model.tvshow.TvShow
import com.king_grey.movie_app.screens.discover.presentation.DiscoverScreen
import com.king_grey.movie_app.screens.home.presentation.HomeScreen
import com.king_grey.movie_app.screens.movie_details.presentation.MovieDetailsScreen
import com.king_grey.movie_app.screens.movie_details.presentation.TvShowDetailsScreen
import com.king_grey.movie_app.screens.mylist.presentation.MyListScreen
import com.king_grey.movie_app.screens.settings.presentation.SettingsScreen


@ExperimentalSharedTransitionApi
@Composable
fun SetUpNavigation(
    navController: NavHostController, startDestination: String, padding: PaddingValues
) {
    SharedTransitionLayout {
        NavHost(navController = navController, startDestination) {

            composable(route = Screen.Splash.route) {
                SplashScreen(navController)
            }

            composable(route = Screen.Home.route) {
                HomeScreen(navController, padding)
            }

            composable(route = Screen.Discover.route) {
                DiscoverScreen(navController, padding, animatedVisibilityScope = this)
            }

            composable(route = Screen.MyList.route) {
                MyListScreen(navController, padding)
            }

            composable(route = Screen.Settings.route) {
                SettingsScreen(navController, padding)
            }

            composable(
                route = Screen.MovieDetails.route,
                arguments = listOf(navArgument("movie") { type = NavType.StringType })
            ) { backStackEntry ->
                val movieJson = backStackEntry.arguments?.getString("movie")
                val movie = movieJson?.let { Gson().fromJson(it, Movie::class.java) }
                movie?.let {
                    MovieDetailsScreen(
                        navController, it, animatedVisibilityScope = this
                    )
                }
            }

            composable(
                route = Screen.TvShowDetails.route,
                arguments = listOf(navArgument("tvShow") { type = NavType.StringType })
            ) { backStackEntry ->
                val tvShowJson = backStackEntry.arguments?.getString("tvShow")
                val tvShow = tvShowJson?.let { Gson().fromJson(it, TvShow::class.java) }
                tvShow?.let {
                    TvShowDetailsScreen(
                        navController, it, animatedVisibilityScope = this
                    )
                }
            }
        }
    }
}
