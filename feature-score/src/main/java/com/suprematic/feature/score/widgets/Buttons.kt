package com.suprematic.feature.score.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.suprematic.domain.entities.Team
import com.suprematic.ui.compositionLocalProviders.ThemeExtras
import com.suprematic.ui.icons.RsIcon

@Composable
fun RoundedIconButton(
    modifier: Modifier,
    icon: RsIcon,
    tint: Color,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled
    ) {
        when (icon) {
            is RsIcon.ImageVectorIcon -> Icon(
                imageVector = icon.imageVector,
                contentDescription = "Start the match",
                modifier = Modifier.fillMaxSize(1.0f),
                tint = tint
            )
            is RsIcon.DrawableResourceIcon -> Icon(
                painter = painterResource(id = icon.id),
                contentDescription = "Start the match",
                modifier = Modifier.fillMaxSize(1.0f),
                tint = tint
            )
        }
    }
}

@Composable
fun NumericButton(
    modifier: Modifier,
    caption: String,
    captionSize: TextUnit,
    containerColor: Color,
    contentColor: Color,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
        enabled = enabled,
        shape = RoundedCornerShape(12.dp),
    ) {
        Text(
            modifier = Modifier.wrapContentSize(),
            text = caption,
            fontSize = captionSize,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun PointsButton(
    team: Team,
    pointsScored: Int,
    containerColor: Color,
    onPointsScored: (Team, Int) -> Unit
) {
    NumericButton(
        modifier = Modifier.size(124.dp),
        caption = pointsScored.toString(),
        captionSize = 48.sp,
        containerColor = containerColor,
        contentColor = ThemeExtras.colors.captionColor
    ) {
        onPointsScored(team, pointsScored)
    }
}

@Composable
fun RowScope.SinglePointButton(
    team: Team,
    pointsScored: Int,
    caption: String,
    containerColor: Color,
    onPointsScored: (Team, Int) -> Unit
) {
    NumericButton(
        modifier = Modifier
            .fillMaxHeight()
            .weight(0.5f)
            .padding(8.dp),
        caption = caption,
        captionSize = 48.sp,
        containerColor = containerColor,
        contentColor = ThemeExtras.colors.captionColor
    ) {
        onPointsScored(team, pointsScored)
    }
}