package com.suprematic.ui.compositionLocalProviders

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

object ThemeExtras {
    val colors: ExtraColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalExtraColorSchemeProvider.current
}
data class ExtraColorScheme(
    val scoreBoardBackgroundColor: Color,
    val buttonOneColor: Color,
    val buttonTwoColor: Color,
    val buttonThreeColor: Color,
    val bigIconColor: Color,
    val smallIconColor: Color,
    val mainCanvasColor: Color,
    val captionColor: Color,
    val captionColorSecondary: Color,
    val captionColorDimmed: Color
)

val extraColorScheme = ExtraColorScheme(
    scoreBoardBackgroundColor = Color(0xFF444B6E),
    buttonOneColor = Color(0xFF3D315B),
    buttonTwoColor = Color(0xFF444B6E),
    buttonThreeColor = Color(0xFF708B75),
    bigIconColor = Color(0xFF3D315B),
    smallIconColor = Color(0xFF708B75),
    mainCanvasColor = Color.LightGray,
    captionColor = Color.White,
    captionColorSecondary = Color(0xFF5D5F61),
    captionColorDimmed = Color.LightGray
)

val LocalExtraColorSchemeProvider = staticCompositionLocalOf<ExtraColorScheme> {
    error("No colors provided")
}