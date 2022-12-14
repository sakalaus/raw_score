package com.suprematic.ui.window

import android.app.Activity
import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.toComposeRect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.window.layout.WindowMetricsCalculator

/**
 * Opinionated set of viewport breakpoints
 *     - Compact: Most phones in portrait mode
 *     - Medium: Most foldables and tablets in portrait mode
 *     - Expanded: Most tablets in landscape mode
 *
 * More info: https://material.io/archive/guidelines/layout/responsive-ui.html
 */
enum class WindowSizeClass { Compact, Medium, Expanded }

/**
 * Remembers the [WindowSizeClass] class for the window corresponding to the current window metrics.
 */
@Composable
fun Activity.rememberWindowSizeClass(): WindowSizeClass {
    // Get the size (in pixels) of the window
    val windowSize = rememberWindowSize()

    // Convert the window size to [Dp]
    val windowDpSize = with(LocalDensity.current) {
        windowSize.toDpSize()
    }

    // Calculate the window size class
    return getWindowSizeClass(windowDpSize)
}

/**
 * Remembers the [Size] in pixels of the window corresponding to the current window metrics.
 */
@Composable
private fun Activity.rememberWindowSize(): Size {
    val configuration = LocalConfiguration.current
    // WindowMetricsCalculator implicitly depends on the configuration through the activity,
    // so re-calculate it upon changes.
    val windowMetrics = remember(configuration) {
        WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(this)
    }
    return windowMetrics.bounds.toComposeRect().size
}

/**
 * Partitions a [DpSize] into a enumerated [WindowSizeClass] class.
 */
@VisibleForTesting
fun getWindowSizeClass(windowDpSize: DpSize): WindowSizeClass = when {
    windowDpSize.width < 0.dp -> throw IllegalArgumentException("Dp value cannot be negative")
    windowDpSize.width < 600.dp -> WindowSizeClass.Compact
    windowDpSize.width < 840.dp -> WindowSizeClass.Medium
    else -> WindowSizeClass.Expanded
}
