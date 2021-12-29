package com.github.stormwryrm.composecustomview.ui.view

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring.StiffnessLow
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.github.stormwryrm.composecustomview.R
import com.github.stormwryrm.composecustomview.utils.BitmapBlur
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun LoginPage() {
    val animatedDegree = remember { Animatable(0f) }
    val animatedScale = remember { Animatable(1f) }
    val animatedRound = remember { Animatable(0f) }
    val animatedColor = remember { androidx.compose.animation.Animatable(Color(0x440000FF)) }
    Box {
        Image(
            bitmap = BitmapBlur.doBlur(
                getBitmap(id = R.drawable.images).asAndroidBitmap(),
                animatedRound.value.toInt() + 20,
                false
            ).asImageBitmap(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .scale(animatedScale.value)
                .clip(QureytoImageShapes())
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(animatedColor.value, shape = CircleShape)
                .align(Alignment.Center)
                .width((130 * animatedScale.value).dp)
                .height((130 * animatedScale.value).dp)
                .pointerInput(Unit) {
                    coroutineScope {
                        while (true) {
                            //点击的位置
                            val offset = awaitPointerEventScope {
                                awaitFirstDown().position
                            }

                            launch {
                                animatedDegree.animateTo(
                                    offset.x,
                                    animationSpec = spring(stiffness = StiffnessLow)
                                )
                            }
                        }
                    }
                }
        ) {
            Image(
                bitmap = getBitmap(id = R.drawable.images),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .background(color = Color(0XFF0DBEBF), shape = CircleShape)
                    .padding(3.dp)
                    .clip(
                        CircleShape
                    )
                    .shadow(elevation = 150.dp, clip = true)
                    .align(Alignment.Center)
                    .rotate(//4.设置角度为初始化到目标x的动画值跟新UI
                        animatedDegree.value
                    )
            )
        }

    }
}

@Composable
fun getBitmap(id: Int) = ImageBitmap.imageResource(id = id)

@Stable
class QureytoImageShapes(var hudu: Float = 150f, var controller: Float = 0f) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path()
        path.moveTo(0f, 0f)
        path.lineTo(0f, size.height - hudu)
        //默认初始化选择中间作为控制点坐标x的数值
        if (controller == 0f) {
            controller = size.width / 2f
        }
        path.quadraticBezierTo(controller, size.height, size.width, size.height - hudu)
        path.lineTo(size.width, 0f)
        path.close()
        return Outline.Generic(path)
    }

}

