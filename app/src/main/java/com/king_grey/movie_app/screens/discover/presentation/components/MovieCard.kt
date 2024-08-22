package com.king_grey.movie_app.screens.discover.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.king_grey.movie_app.screens.discover.data.remote.api.TMDbService
import com.king_grey.movie_app.screens.discover.domain.model.movie.Movie

@Composable
fun MovieCard(movie: Movie, navController: NavHostController) {
    Card(modifier = Modifier.clickable {
        // Navigate here
    }) {
        AsyncImage(
            model = "${TMDbService.IMAGE_BASE_URL}${movie.poster_path}",
            contentDescription = movie.title,
        )
    }
}
