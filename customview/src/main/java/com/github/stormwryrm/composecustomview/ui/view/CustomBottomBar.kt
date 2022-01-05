package com.github.stormwryrm.composecustomview.ui.view

import android.annotation.SuppressLint
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@SuppressLint("RememberReturnType")
@Preview
@Composable
fun CustomBottomBar(count: Int = 3, defaultSelectIndex: Int = 0) {
    val animalCenterIndex = remember { mutableStateOf(defaultSelectIndex) }
    val animalBoolean = remember { mutableStateOf(true) }
    val animalBooleanState: Float by animateFloatAsState(
        if (animalBoolean.value) {
            0f
        } else {
            1f
        }, animationSpec = TweenSpec(durationMillis = 600)

    )

    //点击选择的状态变化,下发到animateFloatAsState里面动画执行开始
    val indexValue: Float by animateFloatAsState(
        when (animalCenterIndex.value) {
            0 -> {
                0f
            }
            1 -> {
                1f
            }
            else -> {
                2f
            }
        },
        //设置动画的格式
        animationSpec = TweenSpec(durationMillis = 500)
    )

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            drawIntoCanvas { canvas ->
                val width = size.width
                val height = size.height

                val paint = Paint().apply {
                    color = Color(0XFF0DBEBF)
                    style = PaintingStyle.Fill
                }
                //固定等分
                val widthOfOne = width / count
                //每一个弧度的中心控制点
                val centerWidthOfOneX = widthOfOne / 2f
                //弧度端口到两遍ONewidth距离
                val marginLeftOrRight = centerWidthOfOneX * 0.3f

                //这个就是移动的过程从动画部分默认第一个选中有弧度。
                val keyAnimal = widthOfOne * indexValue

                canvas.drawCircle(Offset(centerWidthOfOneX + keyAnimal, 0f), 30f, paint)

                canvas.save()
                val path = Path()
                path.moveTo(0f, 0f)
                path.lineTo(marginLeftOrRight + keyAnimal, 0f)
                path.quadraticBezierTo(
                    centerWidthOfOneX + keyAnimal,
                    size.height,
                    widthOfOne - marginLeftOrRight + keyAnimal,
                    0f
                )
                path.lineTo(width, 0f)
                path.lineTo(width, height)
                path.lineTo(0f, height)
                path.close()
                canvas.drawPath(path, paint)
                canvas.restore()
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
        ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "0",
                modifier = Modifier
                    .modifier(animalCenterIndex, 0, animalBooleanState)
                    .clickable {
                        animalCenterIndex.value = 0
                        animalBoolean.value = !animalBoolean.value
                    },
                tint = Color.White
            )

            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "1",
                modifier = Modifier
                    .modifier(animalCenterIndex, 1, animalBooleanState)
                    .clickable {
                        animalCenterIndex.value = 1
                        animalBoolean.value = !animalBoolean.value
                    },
                tint = Color.White
            )
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "2",
                modifier = Modifier
                    .modifier(animalCenterIndex, 2, animalBooleanState)
                    .clickable {
                        animalCenterIndex.value = 2
                        animalBoolean.value = !animalBoolean.value
                    },
                tint = Color.White
            )

        }
    }


}

//将点击选择进行封装起来。单独处理返回按钮位置和是否旋转。
@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.modifier(
    animalCenterIndex: MutableState<Int>,
    i: Int,
    animalBooleanState: Float
): Modifier {
    return if (animalCenterIndex.value == i) {
        Modifier
            .padding(bottom = 57.dp)
            .width(25.dp)
            .height(25.dp)
            .rotate(animalBooleanState * 360)
    } else {
        Modifier
            .padding(top = 20.dp)
            .width(25.dp)
            .height(25.dp)
    }

}
