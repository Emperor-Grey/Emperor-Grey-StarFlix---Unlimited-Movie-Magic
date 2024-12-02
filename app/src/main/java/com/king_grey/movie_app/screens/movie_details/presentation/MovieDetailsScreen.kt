package com.king_grey.movie_app.screens.movie_details.presentation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.king_grey.movie_app.screens.discover.data.remote.api.TMDbService
import com.king_grey.movie_app.screens.discover.domain.model.movie.Movie

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MovieDetailsScreen(
    navController: NavHostController, movie: Movie, animatedVisibilityScope: AnimatedVisibilityScope
) {
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            Color.Transparent,
            MaterialTheme.colorScheme.background.copy(0.2f),
            MaterialTheme.colorScheme.background.copy(0.4f),
            MaterialTheme.colorScheme.background.copy(0.6f),
            MaterialTheme.colorScheme.background.copy(0.8f),
            MaterialTheme.colorScheme.background,
        ), startY = 1570f, endY = 1700f
    )
    rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {  //.verticalScroll(scrollState)
            Box(
                modifier = Modifier
                    .fillMaxHeight(.57f)
                    .fillMaxWidth()
            ) {
                SubcomposeAsyncImage(modifier = Modifier
                    .sharedElement(
                        rememberSharedContentState(key = movie.id),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                    .fillMaxSize(),
                    contentScale = ContentScale.FillBounds,
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("${TMDbService.IMAGE_BASE_URL}${movie.poster_path}")
                        .crossfade(enable = true).build(),
                    contentDescription = movie.title,
                    loading = {
                        Box(contentAlignment = Alignment.Center) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    })

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(gradientBrush)
                )
            }

            repeat(100) {
                Text("Hello World")
            }
        }
    }
}
