package com.suprematic.rawscore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.core.view.WindowCompat
import com.suprematic.rawscore.ui.screens.rsappscreen.RawScoreAppScreen
import com.suprematic.ui.theme.RawScoreTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            RawScoreTheme {
                RawScoreAppScreen(
                    windowSizeClass = calculateWindowSizeClass(this)
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    RawScoreTheme {
//        RawScoreAppScreen()
//    }
//}