package com.suprematic.rawscore.ui.screens.rsappscreen

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.suprematic.rawscore.navigation.RsTopLevelDestination
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
    private val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val windowSizeClass: WindowSizeClass,
) {

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val topLevelDestinations = RsTopLevelDestination.values().asList()

}