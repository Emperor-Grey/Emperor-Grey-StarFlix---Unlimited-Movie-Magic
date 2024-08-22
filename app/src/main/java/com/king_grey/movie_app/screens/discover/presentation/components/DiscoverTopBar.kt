package com.king_grey.movie_app.screens.discover.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoverTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    onSearchClicked: () -> Unit,
    onFilterClicked: () -> Unit,
) {
    Column {
        TopAppBar(scrollBehavior = scrollBehavior, title = { Text("Discover") }, actions = {
            SearchAction(onSearchClicked)
            FilterAction(onFilterClicked)
        })
    }
}

@Composable
fun SearchAction(onSearchClicked: () -> Unit) {
    IconButton(onClick = onSearchClicked) {
        Icon(imageVector = Icons.Default.Search, contentDescription = null)
    }
}

@Composable
fun FilterAction(onFilterClicked: () -> Unit) {
    IconButton(onClick = onFilterClicked) {
        Icon(imageVector = Icons.Default.FilterList, contentDescription = null)
    }
}
