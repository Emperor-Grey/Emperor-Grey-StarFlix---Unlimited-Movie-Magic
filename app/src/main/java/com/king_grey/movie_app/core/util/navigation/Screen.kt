package com.king_grey.movie_app.core.util.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen("Splash")
    data object Home : Screen("Home")
    data object Discover : Screen("Discover")
    data object MyList : Screen("MyList")
    data object MovieDetails : Screen("MovieDetails")
    data object Settings : Screen("Settings")
}
