package com.suprematic.rawscore.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.suprematic.feature.games.navigation.gamesScreen
import com.suprematic.feature.score.navigation.scoreNavigationRoute
import com.suprematic.feature.score.navigation.scoreScreen
import com.suprematic.feature.settings.navigation.settingsScreen

@Composable
fun RsNavHost(
    navController: NavHostController,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = scoreNavigationRoute
) {
    NavHost(
    navController = navController,
    startDestination = startDestination,
    modifier = modifier,
    ) {
        scoreScreen()
        gamesScreen()
        settingsScreen()
    }
}
