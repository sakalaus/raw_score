package com.suprematic.rawscore.ui.screens.rsappscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.suprematic.rawscore.navigation.RsNavHost
import com.suprematic.ui.widgets.RsTopAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RawScoreAppScreen(
    windowSizeClass: androidx.compose.material3.windowsizeclass.WindowSizeClass,
    appState: RsAppState = rememberRsAppState(
        windowSizeClass = windowSizeClass
    ),
) {
    val systemUiController = rememberSystemUiController()
    val darkIcons = isSystemInDarkTheme()

    SideEffect {
        systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = darkIcons)
    }

    Scaffold(
        topBar = {
            RsTopAppBar(
                actionIcon = Icons.Default.MoreVert,
                actionIconContentDescription = "Action icon"
            )
        },
        bottomBar = {
            RsAppBottomBar(
                destinations = appState.topLevelDestinations,
                onNavigateToDestination = appState::navigateToTopLevelDestination,
                currentDestination = appState.currentDestination
            )
        }
    ) { innerPadding ->
        RsNavHost(
            navController = appState.navController,
            onBackClick = appState::onBackClick,
            innerPadding = innerPadding
        )
    }

}