package com.king_grey.movie_app.screens.discover.presentation.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.king_grey.movie_app.screens.discover.presentation.DiscoverViewModel
import com.king_grey.movie_app.screens.discover.presentation.MovieType
import com.king_grey.movie_app.screens.discover.presentation.components.MovieCard

@Composable
fun MoviesTab(navController: NavHostController) {

    val discoverViewModel: DiscoverViewModel = hiltViewModel()

    val movies by discoverViewModel.movies.collectAsState()
    val movieLoading by discoverViewModel.movieLoading.collectAsState()

    LaunchedEffect(Unit) {
        discoverViewModel.fetchMovies(MovieType.Popular)
    }

    if (!movieLoading) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(movies) { movie ->
                MovieCard(movie = movie, navController)
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Preview
@Composable
private fun MoviesTabPrev() {
    MoviesTab(navController = NavHostController(LocalContext.current))
}
