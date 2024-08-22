package com.king_grey.movie_app.screens.discover.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.king_grey.movie_app.core.ui.theme.AppTypography
import com.king_grey.movie_app.screens.discover.presentation.tabs.MoviesTab
import com.king_grey.movie_app.screens.discover.presentation.tabs.TvShowTab
import com.king_grey.movie_app.screens.home.presentation.Tabs

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.DiscoverTabs(
    selectedTabIndex: Int,
    navController: NavHostController,
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
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
    ) { index ->
        val tab = DiscoverTabItems[index]

        when (tab.title) {
            "Movies" -> MoviesTab(navController)
            "TV Shows" -> TvShowTab(navController)

            else -> MoviesTab(navController)
        }
    }
}

val DiscoverTabItems = listOf(
    Tabs("Movies"),
    Tabs("TV Shows"),
)
