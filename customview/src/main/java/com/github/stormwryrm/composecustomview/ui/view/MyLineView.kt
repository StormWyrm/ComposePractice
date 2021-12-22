package com.github.stormwryrm.composecustomview.ui.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview()
@Composable
fun MyLineView() {
    val localDensity = LocalDensity.current
    Canvas(
        modifier = Modifier
            .padding(10.dp)
            .width(200.dp)
            .height(100.dp),
    ) {
        drawIntoCanvas { canvas ->
            val width = size.width
            val height = size.height
            canvas.translate(0f, height)
            canvas.scale(1f, -1f)

            val paint = Paint().apply {
                style = PaintingStyle.Stroke
                color = Color.Gray
                strokeWidth = localDensity.run {
                    1.dp.toPx()
                }
            }

            val path = Path()
            path.relativeLineTo(0f, height)
            path.moveTo(0f, 0f)
            path.relativeLineTo(width, 0f)
            canvas.drawPath(path, paint)


            val dataList = mutableListOf(
                Offset(20f, 60f),
                Offset(40f, 30f),
                Offset(50f, 34f),
                Offset(80f, 54f),
                Offset(100f, 34f),
                Offset(200f, 134f),
                Offset(400f, 154f),
                Offset(480f, 134f)
            )
            val colors: MutableList<Color> = mutableListOf(Color.Red, Color.Blue, Color.Green)

            path.reset()
            dataList.forEach {
                path.lineTo(it.x, it.y)
            }

            //绘制折线
            paint.run {
                color = Color.Blue
                isAntiAlias = true
                shader = LinearGradientShader(Offset(0f, 0f), Offset(width, 0f), colors)
            }
            canvas.drawPath(path, paint)

            //绘制覆盖图
            path.lineTo(dataList.last().x, 0f)
            path.close()
            paint.style = PaintingStyle.Fill
            paint.shader = LinearGradientShader(
                Offset(0f, size.height),
                Offset(0f, 0f),
                arrayListOf(Color(59, 157, 254, 161), Color(59, 157, 254, 21)),
                null,
                TileMode.Clamp
            )
            canvas.drawPath(path, paint)

            //3绘制圆圈
            paint.style= PaintingStyle.Fill
            dataList.forEach {
                canvas.drawCircle(it, 5f, paint)
            }

        }
    }

}

