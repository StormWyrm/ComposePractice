package com.github.stormwyrm.weather

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import coil.compose.rememberImagePainter
import com.blankj.utilcode.util.BarUtils
import com.github.stormwyrm.weather.ui.theme.ComposeDemoTheme

class PictureDetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BarUtils.setStatusBarVisibility(this, false)
        val pictureUrl = intent.getStringExtra(EXTRA_PICTURE_URL)!!
        setContent {
            ComposeDemoTheme {
                PictureDetailView(imageUrl = pictureUrl) {
                    finish()
                }
            }
        }
    }

    companion object {
        const val EXTRA_PICTURE_URL = "EXTRA_PICTURE_URL"

        fun start(context: Context, pictureUrl: String) {
            context.startActivity(Intent(context, PictureDetailActivity::class.java).apply {
                putExtra(EXTRA_PICTURE_URL, pictureUrl)
            })
        }
    }

}

@Composable
fun PictureDetailView(imageUrl: String, click: () -> Unit) {
    var isScaleState by remember {
        mutableStateOf(false)
    }
    var scale by remember {
        mutableStateOf(1f)
    }

    var offsetX by remember {
        mutableStateOf(0f)
    }

    var offsetY by remember {
        mutableStateOf(0f)
    }

    val animbleScale by animateFloatAsState(targetValue = if (isScaleState) 6f else 1f)

    scale = animbleScale
    if (scale == 1f) {
        offsetX = 0f
        offsetY = 0f
    }

    val painter = rememberImagePainter(data = imageUrl, builder = { crossfade(true) })

    val transfromableState = rememberTransformableState { zoomChange, panChange, rotationChange ->
        scale *= zoomChange
        if (scale >= 6.0) scale = 6.0f
        else if (scale <= 1.0) scale = 1.0f
    }

    val draggableStateX = rememberDraggableState {
        offsetX += it * scale
    }

    val draggableStateY = rememberDraggableState {
        offsetY += it * scale
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    translationX = offsetX
                    translationY = offsetY
                }
                .draggable(draggableStateX, Orientation.Horizontal)
                .draggable(draggableStateY, Orientation.Vertical)
                .transformable(state = transfromableState,true)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onDoubleTap = {
                            isScaleState = scale <= 1
                        }
                    )
                }
                .fillMaxSize()
        )
        BackArrowDown {
            click()
        }
    }
}