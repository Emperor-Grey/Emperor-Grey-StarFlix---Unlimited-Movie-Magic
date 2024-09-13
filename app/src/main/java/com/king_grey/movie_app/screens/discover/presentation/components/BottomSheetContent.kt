package com.king_grey.movie_app.screens.discover.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.king_grey.movie_app.screens.discover.presentation.MovieType
import com.king_grey.movie_app.screens.discover.presentation.TvShowType


@Composable
fun FilterBottomSheetContent(
    modifier: Modifier,
    movieTypes: List<MovieType>,
    selectedMovieType: MovieType?,
    onFilterSelected: (MovieType) -> Unit
) {
    Column(modifier = modifier.padding(horizontal = 12.dp)) {
        Text(text = "Select Movie Type", style = MaterialTheme.typography.bodyLarge)
        movieTypes.forEach { movieType ->
            ListItem(headlineContent = { Text(movieType.name) }, trailingContent = {
                if (movieType == selectedMovieType) {
                    Text("✓", style = MaterialTheme.typography.bodyLarge)
                }
            }, modifier = Modifier.clickable {
                onFilterSelected(movieType)
            })
        }
    }
}

@Composable
fun TvShowFilterBottomSheetContent(
    modifier: Modifier,
    tvShowTypes: List<TvShowType>,
    selectedTvShowType: TvShowType?,
    onFilterSelected: (TvShowType) -> Unit
) {
    Column(modifier = modifier.padding(horizontal = 12.dp)) {
        Text(text = "Select TV Show Type", style = MaterialTheme.typography.bodyLarge)
        tvShowTypes.forEach { tvShowType ->
            ListItem(headlineContent = { Text(tvShowType.name) }, trailingContent = {
                if (tvShowType == selectedTvShowType) {
                    Text("✓", style = MaterialTheme.typography.bodyLarge)
                }
            }, modifier = Modifier.clickable {
                onFilterSelected(tvShowType)
            })
        }
    }
}

