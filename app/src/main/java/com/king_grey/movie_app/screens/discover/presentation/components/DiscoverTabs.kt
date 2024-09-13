package com.king_grey.movie_app.screens.discover.presentation.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import com.king_grey.movie_app.core.ui.theme.AppTypography
import com.king_grey.movie_app.screens.discover.domain.model.movie.Movie
import com.king_grey.movie_app.screens.discover.domain.model.tvshow.TvShow
import com.king_grey.movie_app.screens.discover.presentation.tabs.MoviesTab
import com.king_grey.movie_app.screens.discover.presentation.tabs.TvShowTab
import com.king_grey.movie_app.screens.home.presentation.Tabs

@OptIn(
    ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class
)
@Composable
fun SharedTransitionScope.DiscoverTabs(
    selectedTabIndex: Int,
    movies: LazyPagingItems<Movie>,
    tvShows: LazyPagingItems<TvShow>,
    navController: NavHostController,
    animatedVisibilityScope: AnimatedVisibilityScope,
    pagerState: PagerState,
    onTabSelected: (Int) -> Unit,
) {
    SecondaryTabRow(
        selectedTabIndex = selectedTabIndex
    ) {
        DiscoverTabItems.forEachIndexed { index, tab ->
            Tab(selected = selectedTabIndex == index, onClick = { onTabSelected(index) }, text = {
                Text(tab.title, style = AppTypography.titleSmall)
            })
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize(),
    ) { index ->
        val tab = DiscoverTabItems[index]

        when (tab.title) {
            "Movies" -> MoviesTab(movies, navController, animatedVisibilityScope)
            "TV Shows" -> TvShowTab(tvShows, navController, animatedVisibilityScope)

            else -> MoviesTab(movies, navController, animatedVisibilityScope)
        }
    }
}

val DiscoverTabItems = listOf(
    Tabs("Movies"),
    Tabs("TV Shows"),
)
