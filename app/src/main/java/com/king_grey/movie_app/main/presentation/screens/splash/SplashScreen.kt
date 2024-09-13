package com.king_grey.movie_app.main.presentation.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.king_grey.movie_app.R
import com.king_grey.movie_app.core.ui.theme.AppTypography
import com.king_grey.movie_app.core.ui.navigation.Screen

@Composable
fun SplashScreen(
    navController: NavHostController,
) {
    val splashViewModel: SplashScreenViewModel = hiltViewModel()
    val isSplashComplete by splashViewModel.isSplashComplete.collectAsState()

    if (isSplashComplete) {
        LaunchedEffect(Unit) {
            navController.navigate(splashViewModel.startDestination.value) {
                popUpTo(splashViewModel.startDestination.value) { inclusive = true }
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.intro),
                contentDescription = "Intro Screen",
                contentScale = ContentScale.FillBounds
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, bottom = 28.dp)
                    .background(
                        if (isSystemInDarkTheme()) {
                            MaterialTheme.colorScheme.background
                        } else {
                            MaterialTheme.colorScheme.background.copy(0.93f)
                        }, shape = RoundedCornerShape(20.dp)
                    )
                    .align(Alignment.BottomCenter)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Unlimited Entertainment Movie Magic",
                        textAlign = TextAlign.Center,
                        style = AppTypography.headlineLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                    Text(
                        text = "Discover and bookmark your favorite movies. Curate the ultimate bucket list for endless entertainment!",
                        textAlign = TextAlign.Center,
                        style = AppTypography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )

                    Button(modifier = Modifier
                        .fillMaxWidth()
                        .padding(),
                        shape = RoundedCornerShape(16.dp),
                        onClick = {
                            splashViewModel.setIsSplashComplete(completed = true)

                            navController.navigate(Screen.Home.route)
                            navController.popBackStack()
                        }) {
                        Text(
                            text = "Start Your Journey...",
                            modifier = Modifier.padding(12.dp),
                            fontStyle = FontStyle.Italic,
                            fontSize = 16.sp,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun SplashScreenPrev() {
    SplashScreen(navController = NavHostController(LocalContext.current))
}
