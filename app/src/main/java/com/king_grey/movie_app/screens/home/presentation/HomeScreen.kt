package com.king_grey.movie_app.screens.home.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.king_grey.movie_app.screens.home.presentation.components.HomeTabs
import com.king_grey.movie_app.screens.home.presentation.components.HomeTop

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavHostController, padding: PaddingValues) {
    Scaffold {
        var selectedTabIndex by remember { mutableIntStateOf(0) }
        val pagerState = rememberPagerState { mainTabItems.size }

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
                .padding(bottom = padding.calculateBottomPadding())
                .padding(it)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            HomeTop(onSearch = {

            })

            Spacer(modifier = Modifier.height(4.dp))

            HomeTabs(
                selectedTabIndex = selectedTabIndex,
                navController = navController,
                pagerState = pagerState
            ) { index ->
                selectedTabIndex = index
            }
        }
    }
}


val mainTabItems = listOf(
    Tabs("Home"),
    Tabs("Movies"),
    Tabs("Series"),
    Tabs("Drama"),
)

data class Tabs(
    val title: String,
)

@Preview
@Composable
private fun HomeScreenPrev() {
    HomeScreen(
        navController = NavHostController(LocalContext.current), padding = PaddingValues.Absolute()
    )
}
