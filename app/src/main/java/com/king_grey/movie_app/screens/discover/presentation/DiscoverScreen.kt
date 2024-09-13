package com.king_grey.movie_app.screens.discover.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.king_grey.movie_app.screens.discover.presentation.components.DiscoverTabItems
import com.king_grey.movie_app.screens.discover.presentation.components.DiscoverTabs
import com.king_grey.movie_app.screens.discover.presentation.components.DiscoverTopBar
import com.king_grey.movie_app.screens.discover.presentation.components.FilterBottomSheetContent
import com.king_grey.movie_app.screens.discover.presentation.components.TvShowFilterBottomSheetContent
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DiscoverScreen(navController: NavHostController, padding: PaddingValues) {
    val discoverViewModel: DiscoverViewModel = hiltViewModel()
    val scope = rememberCoroutineScope()

    val pagedMovies = discoverViewModel.pagedMovies.collectAsLazyPagingItems()
    val pagedTvShow = discoverViewModel.pagedTvShows.collectAsLazyPagingItems()

    val selectedMovieType by discoverViewModel.movieType.collectAsState()
    val selectedTvShowType by discoverViewModel.tvShowType.collectAsState()

    val selectedTabIndex by discoverViewModel.selectedTabIndex.collectAsState()
    val pagerState = rememberPagerState(pageCount = { DiscoverTabItems.size })

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val sheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = SheetState(false, LocalDensity.current, SheetValue.Hidden)
    )

    val sheetContent: @Composable ColumnScope.() -> Unit = {
        when (selectedTabIndex) {
            0 -> FilterBottomSheetContent(modifier = Modifier.padding(bottom = padding.calculateBottomPadding()),
                movieTypes = MovieType.entries,
                selectedMovieType = selectedMovieType,
                onFilterSelected = { selectedType ->
                    discoverViewModel.setMovieType(selectedType)
                    scope.launch { sheetState.bottomSheetState.hide() }
                })

            1 -> TvShowFilterBottomSheetContent(modifier = Modifier.padding(bottom = padding.calculateBottomPadding()),
                tvShowTypes = TvShowType.entries,
                selectedTvShowType = selectedTvShowType,
                onFilterSelected = { selectedType ->
                    discoverViewModel.setTvShowType(selectedType)
                    scope.launch { sheetState.bottomSheetState.hide() }
                })

            else -> FilterBottomSheetContent(modifier = Modifier.padding(bottom = padding.calculateBottomPadding()),
                movieTypes = MovieType.entries,
                selectedMovieType = selectedMovieType,
                onFilterSelected = { selectedType ->
                    discoverViewModel.setMovieType(selectedType)
                    scope.launch { sheetState.bottomSheetState.hide() }
                })
        }
    }

    BottomSheetScaffold(scaffoldState = sheetState,
        sheetPeekHeight = 0.dp,
        sheetContent = sheetContent,
        topBar = {
            DiscoverTopBar(scrollBehavior = scrollBehavior,
                onSearchClicked = {},
                onFilterClicked = {
                    scope.launch { sheetState.bottomSheetState.expand() }
                })
        },
        content = { innerPadding ->
            LaunchedEffect(selectedTabIndex) {
                pagerState.animateScrollToPage(selectedTabIndex)
            }

            LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
                if (!pagerState.isScrollInProgress) {
                    discoverViewModel.setSelectedTabIndex(pagerState.currentPage)
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(scrollBehavior.nestedScrollConnection)
                    .padding(innerPadding)
            ) {
                DiscoverTabs(
                    selectedTabIndex = selectedTabIndex,
                    movies = pagedMovies,
                    tvShows = pagedTvShow,
                    navController = navController,
                    pagerState = pagerState
                ) { index ->
                    discoverViewModel.setSelectedTabIndex(index)
                }
            }
        })
}
