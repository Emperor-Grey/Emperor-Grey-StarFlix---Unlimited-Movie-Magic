package com.king_grey.movie_app.screens.discover.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.king_grey.movie_app.screens.discover.presentation.components.DiscoverTabItems
import com.king_grey.movie_app.screens.discover.presentation.components.DiscoverTabs
import com.king_grey.movie_app.screens.discover.presentation.components.DiscoverTopBar

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DiscoverScreen(navController: NavHostController) {
    val discoverViewModel: DiscoverViewModel = hiltViewModel()
    val pagedMovies = discoverViewModel.pagedMovies.collectAsLazyPagingItems()
    val pagedTvShow = discoverViewModel.pagedTvShows.collectAsLazyPagingItems()

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState(pageCount = { DiscoverTabItems.size })

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(topBar = {
        DiscoverTopBar(scrollBehavior, onSearchClicked = {}, onFilterClicked = {})
    }, content = { padding ->

        LaunchedEffect(selectedTabIndex) {
            pagerState.animateScrollToPage(selectedTabIndex)
        }

        LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
            if (!pagerState.isScrollInProgress) {
                selectedTabIndex = pagerState.currentPage
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .padding(padding)
        ) {

            DiscoverTabs(
                selectedTabIndex = selectedTabIndex,
                movies = pagedMovies,
                tvShows = pagedTvShow,
                navController = navController,
                pagerState = pagerState
            ) { index ->
                selectedTabIndex = index
            }
        }
    })
}
