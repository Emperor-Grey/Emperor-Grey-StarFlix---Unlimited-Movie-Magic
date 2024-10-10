package com.king_grey.movie_app.screens.movie_details.presentation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.king_grey.movie_app.screens.discover.data.remote.api.TMDbService
import com.king_grey.movie_app.screens.discover.domain.model.tvshow.TvShow

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.TvShowDetailsScreen(
    navController: NavHostController,
    tvShow: TvShow,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        val screenHeight = LocalConfiguration.current.screenHeightDp.dp 
        val imageHeight = screenHeight * 0.63f

        Column(modifier = Modifier.fillMaxHeight()) {
            SubcomposeAsyncImage(modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight)
                .sharedElement(
                    rememberSharedContentState(key = tvShow.id),
                    animatedVisibilityScope = animatedVisibilityScope
                ),
                contentScale = ContentScale.FillBounds,
                model = ImageRequest.Builder(LocalContext.current)
                    .data("${TMDbService.IMAGE_BASE_URL}${tvShow.poster_path}")
                    .crossfade(enable = true).build(),
                contentDescription = tvShow.name,
                loading = {
                    Box(contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                })
        }
    }
}