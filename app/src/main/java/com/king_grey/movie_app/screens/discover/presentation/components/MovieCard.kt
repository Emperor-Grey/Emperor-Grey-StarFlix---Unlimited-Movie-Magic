package com.king_grey.movie_app.screens.discover.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.king_grey.movie_app.screens.discover.data.remote.api.TMDbService
import com.king_grey.movie_app.screens.discover.domain.model.movie.Movie

@Composable
fun MovieCard(movie: Movie, navController: NavHostController) {
    Card(modifier = Modifier.clickable {
        // Navigate here
    }) {
        SubcomposeAsyncImage(model = ImageRequest.Builder(LocalContext.current)
            .data("${TMDbService.IMAGE_BASE_URL}${movie.poster_path}").crossfade(enable = true)
            .build(), contentDescription = movie.title, loading = {
            Box(Modifier.size(125.dp, 185.dp), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        })
    }
}
