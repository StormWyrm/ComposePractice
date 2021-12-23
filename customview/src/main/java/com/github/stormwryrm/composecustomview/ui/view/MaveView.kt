package com.github.stormwryrm.composecustomview.ui.view

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun WaveView() {
    Box(
        Modifier
            .fillMaxSize()
    ) {
        val offset = remember {
            Animatable(0f)
        }
        val localDenisty = LocalDensity.current
        LaunchedEffect(key1 = Unit, block = {
            offset.animateTo(
                localDenisty.run { 150.dp.toPx() },
                animationSpec = repeatable(
                    Int.MAX_VALUE,
                    tween(1000, easing = LinearEasing),
                )
            )
        })
        Canvas(
            modifier = Modifier
                .size(300.dp, 300.dp)
                .align(Alignment.Center)
        ) {
            drawIntoCanvas { canvas ->
                val width = size.width
                val height = size.height
                val waveWidth = width / 4

                val paint = Paint().apply {
                    color = Color.Gray
                    style = PaintingStyle.Stroke
                    strokeWidth = 3f

                }
                canvas.run {
                    translate(0f, height)
                    scale(1f, -1f)
//                    drawLine(Offset(0f, 0f), Offset(width, 0f), paint)
//                    drawLine(Offset(0f, 0f), Offset(0f, height), paint)
                }

                canvas.clipPath(Path().apply {
                    addRoundRect(
                        RoundRect(
                            Rect(0f, height, waveWidth * 2, 0f),
                            CornerRadius(30f, 30f)
                        )
                    )
                })

                canvas.translate(-offset.value, 0f)
                //绘制水波纹
                canvas.save()
//                canvas.drawLine(Offset(0f, 0f), Offset(width, 0f), paint)

                paint.shader = LinearGradientShader(
                    Offset(0f, 1.5f * waveWidth), Offset(0f, 0f),
                    mutableListOf(Color(98, 179, 243, 255), Color(7, 94, 133, 255))
                )
                paint.style = PaintingStyle.Fill
                val path = Path()
                path.lineTo(0f, waveWidth)
                path.cubicTo(
                    waveWidth * 0.5f,
                    waveWidth * 1.3f,
                    waveWidth * 1.5f,
                    waveWidth * 0.7f,
                    waveWidth * 2,
                    waveWidth
                )

                path.cubicTo(
                    waveWidth * 2.5f,
                    waveWidth * 1.3f,
                    waveWidth * 3.5f,
                    waveWidth * 0.7f,
                    waveWidth * 4,
                    waveWidth
                )
                path.lineTo(width, 0f)
                path.close()

                canvas.drawPath(path, paint)
                canvas.restore()

            }
        }
    }
}
