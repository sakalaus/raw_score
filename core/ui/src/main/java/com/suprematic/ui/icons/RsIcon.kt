package com.suprematic.ui.icons

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.suprematic.ui.R

object RsIcons {
    val Undo = R.drawable.undo
}

sealed class RsIcon {
    data class ImageVectorIcon(val imageVector: ImageVector) : RsIcon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : RsIcon()
}
