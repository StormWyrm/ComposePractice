package com.github.stormwryrm.composecustomview.ui.view

import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Rect
import android.text.TextPaint
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun TextGradientView(modifier: Modifier = Modifier, text: String = "我是一个粉刷匠，粉刷本领强。") {
    val translateAnim = remember {
        Animatable(0f)
    }
    val gradientMatrix = remember {
        Matrix()
    }

    val paint = TextPaint().apply {
        style = Paint.Style.FILL
        textSize = 60f
    }

    val bounds = Rect()
    paint.getTextBounds(text, 0, text.length, bounds)


    LaunchedEffect(key1 = Unit, block = {
        translateAnim.animateTo(
            bounds.width().toFloat(), animationSpec = repeatable(
                Int.MAX_VALUE,
                tween(1000, easing = LinearEasing),
                RepeatMode.Reverse
            )
        )
    })

    gradientMatrix.setTranslate(translateAnim.value, 0f)

    Canvas(
        modifier = modifier
    ) {

        drawIntoCanvas {
            val canvas = it.nativeCanvas
            paint.shader = LinearGradientShader(
                Offset.Zero, Offset(bounds.width().toFloat(), 0f),
                mutableListOf(Color(0xFF87E9B8), Color(0xFFBE1515), Color(0xFFF0D90B))
            ).apply {
                setLocalMatrix(gradientMatrix)
            }
            canvas.drawText(text, 0f, bounds.height().toFloat(), paint)
        }
    }
}
