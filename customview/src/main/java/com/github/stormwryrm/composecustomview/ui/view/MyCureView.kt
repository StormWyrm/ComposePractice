package com.github.stormwryrm.composecustomview.ui.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun MyCurveView() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawIntoCanvas { canvas ->
            val width = size.width
            val height = size.height

            //创建画笔
            val paint = Paint().apply {
                style = PaintingStyle.Fill
                color = Color.Green
            }

            //修改坐标系
            canvas.translate(0f, -height)
            canvas.scale(1f, -1f)


        }
    }
}

@Composable
fun DrawScope.drawCurve(points: List<Offset>, defaultSpacing: Float = 20f, canvas: Canvas) {
    val localDensity = LocalDensity.current
    val paint = Paint().apply {
        color = Color.Gray
        style = PaintingStyle.Stroke
        strokeWidth = localDensity.run {
            1.dp.toPx()
        }
    }
    val path = Path()
    path.moveTo(points[0].x, points[0].y)
    canvas.run {
        for (index in 0 until points.size - 1) {
            val curPoint = points[index]
            val nextPoint = points[index + 1]
            if (curPoint.y == nextPoint.y) {
                path.lineTo(nextPoint.x, nextPoint.y)
            } else {
                val centerX = (curPoint.x + nextPoint.x) / 2
                val centerY = (curPoint.y + curPoint.y) / 2
                val control1X = (curPoint.x + centerX) / 2
                val control1Y = (curPoint.y + centerY) / 2
                val control2X = (nextPoint.x + centerX) / 2
                val control2Y = (nextPoint.y + centerY) / 2
                if (curPoint.y < nextPoint.y) {
                    path.cubicTo(
                        control1X - 10,
                        control1Y,
                        control2X - 10,
                        control2Y,
                        nextPoint.x,
                        nextPoint.y
                    )
                } else {
                    path.cubicTo(
                        control1X + 10,
                        control1Y,
                        control2X + 10,
                        control2Y,
                        nextPoint.x,
                        nextPoint.y
                    )
                }
            }
        }
        drawPath(path, paint)
    }
}


