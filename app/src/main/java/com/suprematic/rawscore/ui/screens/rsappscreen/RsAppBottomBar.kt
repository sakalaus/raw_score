package com.suprematic.rawscore.ui.screens.rsappscreen

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.suprematic.rawscore.navigation.RsTopLevelDestination
import com.suprematic.ui.widgets.RsNavigationBar
import com.suprematic.ui.widgets.RsNavigationBarItem

@Composable
fun RsAppBottomBar(
    destinations: List<RsTopLevelDestination>,
    onNavigateToDestination: (RsTopLevelDestination) -> Unit,
    currentDestination: NavDestination?
) {
    RsNavigationBar {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            RsNavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    val icon = if (selected) {
                        destination.selectedIcon
                    } else {
                        destination.unselectedIcon
                    }
                    Icon(
                        imageVector = icon,
                        contentDescription = null
                    )
                },
                label = { Text(stringResource(destination.iconTextId)) }
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: RsTopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false

