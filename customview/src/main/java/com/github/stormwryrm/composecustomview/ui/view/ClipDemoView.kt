package com.github.stormwryrm.composecustomview.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.stormwryrm.composecustomview.R

@Preview
@Composable
fun CircleClipDemo() {
    Image(
        painter = painterResource(id = R.drawable.images),
        contentScale = ContentScale.Crop,
        contentDescription = null,
        modifier = Modifier
            .clip(shape = CircleShape)
            .size(100.dp)
    )
}

@Preview
@Composable
fun RoundedClipDemo() {
    Image(
        painter = painterResource(id = R.drawable.images),
        contentScale = ContentScale.Crop,
        contentDescription = null,
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .size(100.dp)
    )
}

@Preview
@Composable
fun CustomClipDemo() {
    Image(
        painter = painterResource(id = R.drawable.images),
        contentScale = ContentScale.Crop,
        contentDescription = null,
        modifier = Modifier
            .clip(shape = RectangleCustomImageShapes)
            .size(100.dp)
    )
}

@Preview
@Composable
fun CustomSemicircleClipDemo() {
    Image(
        painter = painterResource(id = R.drawable.images),
        contentScale = ContentScale.Crop,
        contentDescription = null,
        modifier = Modifier
            .clip(shape = SemicircleCustomImageShapes)
            .size(100.dp)
    )
}

@Stable
val RectangleCustomImageShapes: Shape = object : Shape {
    val corner = 80f
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            moveTo(corner, 0f)
            quadraticBezierTo(0f, 0f, 0f, corner)
            lineTo(0f, size.height - corner)
            quadraticBezierTo(0f, size.height, corner, size.height)
            lineTo(size.width, size.height)
            lineTo(size.width, 0f)
            lineTo(corner, 0f)
        }
        return Outline.Generic(path)
    }

}

@Stable
val SemicircleCustomImageShapes: Shape = object : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val corner = size.width / 2f

        val path = Path().apply {
            moveTo(corner, 0f)
            quadraticBezierTo(0f, 0f, 0f, corner)
            lineTo(0f, size.height - corner)
            quadraticBezierTo(0f, size.height, corner, size.height)
            lineTo(size.width, size.height)
            lineTo(size.width, 0f)
            lineTo(corner, 0f)
        }
        return Outline.Generic(path)
    }

}

@Preview
@Composable
fun StudyLayoutViews() {
    Box(
        modifier = Modifier
            .size(200.dp, height = 100.dp)
            .clip(BoxClipShape)
            .background(Color(206, 236, 250))
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.images),
                contentDescription = "w",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
                    .background(Color.White, shape = CircleShape)
                    .padding(3.dp)
                    .clip(
                        CircleShape
                    )
                    .shadow(elevation = 150.dp, clip = true)
            )
            Column(modifier = Modifier.padding(start = 5.dp)) {
                Text(
                    "Container",
                    fontSize = 16.sp,
                    color = Color.Black,
                )
                Text(
                    "容器组件",
                    modifier = Modifier.padding(top = 3.dp, bottom = 3.dp),
                    fontSize = 12.sp,
                    color = Color.Gray,
                )
                Text(
                    "123万阅读量",
                    fontSize = 8.sp,
                    color = Color.White,
                )
            }
            Box(
                modifier = Modifier
                    .height(60.dp)
                    .padding(start = 30.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Image(
                    painter = painterResource(id = R.drawable.images),                    contentDescription = "w",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .height(20.dp)
                        .width(20.dp)
                        .shadow(elevation = 150.dp, clip = true)

                )
            }
        }
    }
}

@Stable
val BoxClipShape: Shape = object : Shape {
    val radius = 30f
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {

        val path = Path().apply {
            moveTo(radius, 0f)
            lineTo(0f, radius)
            lineTo(0f, size.height - radius)
            lineTo(radius, size.height)
            lineTo((size.width * 0.25).toFloat(), size.height)
            relativeLineTo(radius / 2, -radius / 2)
            relativeLineTo(size.width / 2 - radius, 0f)
            relativeLineTo(radius / 2, radius / 2)
            lineTo(size.width - radius, size.height)
            relativeLineTo(radius, -radius)
            relativeLineTo(0f, -size.height + 2 * radius)
            relativeLineTo(-radius, -radius)
            relativeLineTo(-(size.width * 0.25 - radius).toFloat(), 0f)
            relativeLineTo(-radius / 2, radius / 2)
            relativeLineTo(-size.width / 2 + radius, 0f)
            relativeLineTo(-radius / 2, -radius / 2)
            close()
        }
        return Outline.Generic(path)
    }

}