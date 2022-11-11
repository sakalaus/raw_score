package com.suprematic.rawscore.ui.screens.rsappscreen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun RawScoreAppScreen(
    windowSizeClass: androidx.compose.material3.windowsizeclass.WindowSizeClass
) {
    val systemUiController = rememberSystemUiController()
    val darkIcons = isSystemInDarkTheme()
    val navController = rememberNavController()
    
    SideEffect {
        systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = darkIcons)
    }

}