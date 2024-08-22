package com.king_grey.movie_app.screens.home.presentation.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeTop(onSearch: (String) -> Unit) {
    var isActive by remember {
        mutableStateOf(false)
    }
    var query by remember {
        mutableStateOf("")
    }


    MySearchBar(modifier = Modifier.fillMaxWidth(),
        placeHolderText = { Text("Search for movies, series and more") },
        query = query,
        onQueryChange = {
            query = it
        },
        onSearch = onSearch,
        active = isActive,
        onActiveChange = {
            isActive = it
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchBar(
    modifier: Modifier,
    placeHolderText: @Composable () -> Unit,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    active: Boolean,
    onActiveChange: (Boolean) -> Unit,
) {
    val context = LocalContext.current

    Box(
        modifier.semantics { isTraversalGroup = true },
        contentAlignment = Alignment.Center,
    ) {
        DockedSearchBar(modifier = Modifier
            .fillMaxWidth(0.94f)
            .align(Alignment.TopCenter)
            .semantics {
                traversalIndex = 0f
            }, placeholder = placeHolderText, query = query, leadingIcon = {
            if (!active) {
                Icon(
                    imageVector = Icons.Filled.Search, contentDescription = "Search"
                )
            } else {
                Icon(
                    modifier = Modifier.clickable(onClick = {
                        onActiveChange(false)
                        onQueryChange("")
                    }),
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back button"
                )
            }
        }, trailingIcon = {
            if (!active) {
                UserHead("king_grey", "King", "Grey")
            } else {
                Icon(
                    modifier = Modifier.clickable(onClick = {
                        if (query.isEmpty()) {
                            onActiveChange(false)
                        } else {
                            onQueryChange("")
                        }
                    }), imageVector = Icons.Default.Clear, contentDescription = "Clear button"
                )
            }
        }, enabled = true, onQueryChange = onQueryChange, onSearch = {
            onSearch(it)
            if (query.isNotEmpty()) {
                onActiveChange(false)
            } else {
                Toast.makeText(
                    context, "Please enter something to search", Toast.LENGTH_SHORT
                ).show()
            }

        }, active = active, onActiveChange = onActiveChange, content = {
            Column(Modifier.padding(16.dp)) {
                // Text(text = "Your Suggestions appear here", Modifier.padding(8.dp))
            }
        })
    }
}

@Preview
@Composable
private fun HomeTopPrev() {
    HomeTop(onSearch = {})
}
