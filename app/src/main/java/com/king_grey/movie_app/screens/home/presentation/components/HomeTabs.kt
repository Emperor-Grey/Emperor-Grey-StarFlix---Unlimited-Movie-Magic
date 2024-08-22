package com.king_grey.movie_app.screens.home.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.navigation.NavHostController
import com.king_grey.movie_app.core.ui.theme.AppTypography
import com.king_grey.movie_app.screens.home.presentation.mainTabItems
import com.king_grey.movie_app.screens.home.presentation.tabs.DramaContent
import com.king_grey.movie_app.screens.home.presentation.tabs.HomeContent
import com.king_grey.movie_app.screens.home.presentation.tabs.MoviesContent
import com.king_grey.movie_app.screens.home.presentation.tabs.SeriesContent

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.HomeTabs(
    selectedTabIndex: Int,
    pagerState: PagerState,
    navController: NavHostController,
    onTabSelected: (Int) -> Unit,
) {
    PrimaryTabRow(selectedTabIndex = selectedTabIndex) {
        mainTabItems.forEachIndexed { index, tab ->
            Tab(modifier = Modifier.clip(CircleShape),
                selected = selectedTabIndex == index,
                onClick = { onTabSelected(index) },
                text = {
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
        val tab = mainTabItems[index]

        when (tab.title) {
            "Home" -> HomeContent(navController)
            "Movies" -> MoviesContent(navController)
            "Series" -> SeriesContent(navController)
            "Drama" -> DramaContent(navController)

            else -> HomeContent(navController)
        }
    }
}
