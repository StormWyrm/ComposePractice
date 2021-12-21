package com.github.stormwryrm.composecustomview.ui.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun CurveView() {
    val localDensity = LocalDensity.current

    Canvas(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
        drawIntoCanvas { canvas ->
            canvas.translate(0f, size.height)
            canvas.scale(1f, -1f)

            val paint = Paint().apply {
                color = Color.Gray
                style = PaintingStyle.Stroke
                strokeWidth = localDensity.run {
                    1.dp.toPx()
                }
            }
            val datas = mutableListOf(
                Offset(0f, 20f),
                Offset(100f, 20f),
                Offset(200f, 200f),
                Offset(400f, 100f),
                Offset(480f, 300f)
            )

            val path = Path()
            path.moveTo(datas[0].x, datas[0].y)
            for (index in 0 until datas.size - 1) {
                val cur = datas[index]
                val next = datas[index + 1]
                if (cur.y == next.y) {
                    path.moveTo(next.x, next.y)
                } else {
                    val centerX = (cur.x + next.x) / 2
                    val centerY = (cur.y + next.y) / 2
                    val control1X = (cur.x + centerX) / 2
                    val control1Y = (cur.y + centerY) / 2
                    val control2X = (next.x + centerX) / 2
                    val control2Y = (next.y + centerY) / 2
                    if (cur.y < next.y) {
                        path.cubicTo(
                            control1X - 30,
                            control1Y,
                            control2X - 30,
                            control2Y,
                            next.x,
                            next.y
                        )
                    } else {
                        path.cubicTo(
                            control1X + 30,
                            control1Y,
                            control2X + 30,
                            control2Y,
                            next.x,
                            next.y
                        )
                    }
                }
            }
            canvas.drawPath(path, paint)

        }
    }
}