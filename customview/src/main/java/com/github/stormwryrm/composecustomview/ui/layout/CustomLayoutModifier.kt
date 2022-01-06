package com.github.stormwryrm.composecustomview.ui.layout

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp

fun Modifier.firstBaseLineToTop(
    firstBaseLineToTop: Dp
) = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
    val firstBaseLine = placeable[FirstBaseline]
    val placeableY = firstBaseLineToTop.roundToPx() -placeable.height
    layout(placeable.width, placeable.height) {
        placeable.placeRelative(0, placeableY)
    }
}
