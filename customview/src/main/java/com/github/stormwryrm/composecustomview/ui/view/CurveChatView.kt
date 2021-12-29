package com.github.stormwryrm.composecustomview.ui.view

import android.text.TextPaint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun CurveChatView() {
    val localDensity = LocalDensity.current

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        drawIntoCanvas { canvas ->
            val viewWidth = size.width
            val viewHeight = size.height

            val leftPadding = 180f
            val bottomPadding = 240f
            val rightPadding = 80f
            val lineWidth = viewWidth - leftPadding - rightPadding
            val lineSpacing = lineWidth / 6
            val paint = Paint().apply {
                color = Color(188, 188, 188)
                style = PaintingStyle.Stroke
                strokeWidth = localDensity.run {
                    1.dp.toPx()
                }
            }

            canvas.scale(1f, -1f)
            canvas.translate(leftPadding, -(size.height - bottomPadding))

            //绘制平行线
            canvas.save()
            repeat(4) {
                canvas.drawLine(Offset(0f, 0f), Offset(lineWidth, 0f), paint)
                canvas.translate(0f, lineSpacing)
            }
            canvas.restore()

            //绘制文字
            drawText(canvas.nativeCanvas, lineSpacing)

            //绘制折线
            drawCaves(canvas, paint, lineSpacing)

        }
    }
}

private fun drawText(canvas: NativeCanvas, lineSpacing: Float) {
    val textPaint = TextPaint().apply {
        strokeWidth = 2f
        style = android.graphics.Paint.Style.FILL
        color = android.graphics.Color.argb(100, 111, 111, 111)
        textSize = 24f
    }
    val bounds = android.graphics.Rect()
    canvas.save()
    canvas.scale(1f, -1f)
    canvas.save()
    repeat(7) {
        val strTx = "11.${11 + it}"
        textPaint.getTextBounds(strTx, 0, strTx.length, bounds)
        canvas.drawText(
            strTx,
            0,
            strTx.length,
            -bounds.width() / 2f,
            bounds.height().toFloat() * 2.5f,
            textPaint
        )
        canvas.translate(lineSpacing, 0f)
    }
    canvas.restore()

    repeat(4) { index ->
        var strTx = if (index == 0) {
            "${index}"
        } else if (index == 1) {
            "${500}"
        } else if (index == 2) {
            "1k"
        } else {
            "1.5k"
        }
        textPaint.getTextBounds(strTx, 0, strTx.length, bounds)
        canvas.drawText(
            strTx,
            0,
            strTx.length,
            -bounds.width() - 50f,
            bounds.height().toFloat() / 2,
            textPaint
        )
        canvas.translate(0f, -lineSpacing)
    }
    canvas.restore()
}

private fun drawCaves(
    canvas: Canvas,
    paint: Paint,
    lineSpacing: Float
) {
    val xMoveDistance = 40
    val yMoveDistance = 40

    paint.color =  Color(243, 134, 110, 255)
    paint.strokeWidth = 3f

    val datas = mutableListOf(
        Offset(0f, 0f),
        Offset(1f, 0f),
        Offset(2f, 0f),
        Offset(3f, 2.5f),
        Offset(4f, 0.8f),
        Offset(5f, 2.5f),
        Offset(6f, 0f)
    ).map {
        Offset(it.x * lineSpacing, it.y * lineSpacing)
    }

    paint.style =  PaintingStyle.Stroke
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
                    control1X + xMoveDistance,
                    control1Y - yMoveDistance,
                    control2X - xMoveDistance,
                    control2Y + yMoveDistance,
                    next.x,
                    next.y
                )
            } else {
                path.cubicTo(
                    control1X + xMoveDistance,
                    control1Y + yMoveDistance,
                    control2X - xMoveDistance,
                    control2Y - yMoveDistance,
                    next.x,
                    next.y
                )
            }
        }
    }

    canvas.drawPath(path, paint)
    paint.apply {
        paint.style = PaintingStyle.Fill
        shader = LinearGradientShader(
            Offset(0f, 1500f), Offset(0f, 0f),
            mutableListOf(
                Color(243, 134, 110, 255),
                Color(240, 224, 216, 255),
            )
        )
    }
    path.close()
    canvas.drawPath(path, paint)

    datas.forEach {
        paint.style =  PaintingStyle.Fill
        canvas.drawCircle(it, 7f, paint)
    }
}
