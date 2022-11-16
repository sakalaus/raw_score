package com.suprematic.rawscore.ui.screens.rsappscreen

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.suprematic.feature.games.navigation.gamesNavigationRoute
import com.suprematic.feature.games.navigation.navigateToGames
import com.suprematic.feature.score.navigation.navigateToScore
import com.suprematic.feature.score.navigation.scoreNavigationRoute
import com.suprematic.feature.settings.navigation.navigateToSettings
import com.suprematic.feature.settings.navigation.settingsNavigationRoute
import com.suprematic.rawscore.navigation.RsTopLevelDestination
import com.suprematic.rawscore.navigation.RsTopLevelDestination.*
import kotlinx.coroutines.CoroutineScope


@Composable
fun rememberRsAppState(
    windowSizeClass: WindowSizeClass,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController()
): RsAppState {
    return remember(navController, coroutineScope, windowSizeClass) {
        RsAppState(navController, coroutineScope, windowSizeClass)
    }
}

class RsAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val windowSizeClass: WindowSizeClass,
) {

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: RsTopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            scoreNavigationRoute -> SCORE
            gamesNavigationRoute -> GAMES
            settingsNavigationRoute -> SETTINGS
            else -> null
        }

    val topLevelDestinations = values().asList()

    fun navigateToTopLevelDestination(topLevelDestination: RsTopLevelDestination) {

            val topLevelNavOptions = navOptions {

                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }

            when (topLevelDestination) {
                SCORE -> navController.navigateToScore(topLevelNavOptions)
                GAMES -> navController.navigateToGames(topLevelNavOptions)
                SETTINGS -> navController.navigateToSettings(topLevelNavOptions)
            }
    }

    fun onBackClick() {
        navController.popBackStack()
    }

}