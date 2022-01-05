package com.github.stormwryrm.composecustomview.ui.view

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun CustomTextField() {
    var isFocused by remember {
        mutableStateOf(false)
    }
    val animRadius = animateFloatAsState(targetValue = if (isFocused) 30f else 0f)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextField(
            value = "",
            onValueChange = {},
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent
            ),
            modifier = Modifier
                .height(48.dp)
                .border(
                    1.2.dp,
                    Color(0xFFC0AFE0),
                    shape = AnimatedRoundedCornerShape(animRadius.value)
                )
                .onFocusChanged {
                    isFocused = it.isFocused
                },
            leadingIcon = {
                Icon(
                    Icons.Default.Call,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(15.dp)
                )
            }
        )
    }
}

class AnimatedRoundedCornerShape(val radius: Float = 30f) : Shape {
    val corner = 30f
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path()
        path.lineTo(corner, 0f)
        path.quadraticBezierTo(0f, 0f, 0f, corner)
        path.lineTo(0f, size.height - corner)
        path.quadraticBezierTo(0f, size.height, corner, size.height)
        path.quadraticBezierTo(
            size.width / 2,
            size.height - radius,
            size.width - corner,
            size.height
        )
        path.quadraticBezierTo(size.width, size.height, size.width, size.height - corner)
        path.lineTo(size.width, corner)
        path.quadraticBezierTo(size.width, 0f, size.width - corner, 0f)
        path.quadraticBezierTo(
            size.width / 2,
            radius,
            radius,
            0f
        )
        return Outline.Generic(path)
    }

}