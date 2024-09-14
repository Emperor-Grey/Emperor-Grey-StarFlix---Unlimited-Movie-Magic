package com.king_grey.movie_app.screens.discover.presentation.tabs

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.king_grey.movie_app.screens.discover.domain.model.movie.Movie
import com.king_grey.movie_app.screens.discover.presentation.components.MovieCard

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MoviesTab(
    movies: LazyPagingItems<Movie>,
    navController: NavHostController,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    when {
        movies.loadState.refresh is LoadState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        movies.loadState.refresh is LoadState.Error -> {
            val error = (movies.loadState.refresh as LoadState.Error).error
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Error: ${error.localizedMessage}", color = Color.Red)
            }
        }

        movies.itemCount == 0 -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "No Movies Available")
            }
        }

        else -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(movies.itemCount) { index ->
                    movies[index]?.let { movie ->
                        MovieCard(
                            movie = movie, navController = navController, animatedVisibilityScope
                        )
                    }
                }


                if (movies.loadState.append is LoadState.Loading) {
                    item {
                        Box(
                            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                if (movies.loadState.append is LoadState.Error) {
                    val appendError = (movies.loadState.append as LoadState.Error).error
                    item {
                        Box(
                            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Error loading more movies: ${appendError.localizedMessage}")
                        }
                    }
                }
            }
        }
    }
}
