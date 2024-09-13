package com.king_grey.movie_app.core.ui.navigation

import android.net.Uri
import com.google.gson.Gson
import com.king_grey.movie_app.screens.discover.domain.model.movie.Movie
import com.king_grey.movie_app.screens.discover.domain.model.tvshow.TvShow

sealed class Screen(val route: String) {
    data object Splash : Screen("Splash")

    data object Home : Screen("Home")
    data object Discover : Screen("Discover")
    data object MyList : Screen("MyList")
    data object Settings : Screen("Settings")

    data object MovieDetails : Screen("MovieDetails/{movie}") {
        fun createRoute(movie: Movie): String {
            return "MovieDetails/${Uri.encode(Gson().toJson(movie))}"
        }
    }

    data object TvShowDetails : Screen("MovieDetails/{tvShow}") {
        fun createRoute(tvShow: TvShow): String {
            return "MovieDetails/${Uri.encode(Gson().toJson(tvShow))}"
        }
    }
}
