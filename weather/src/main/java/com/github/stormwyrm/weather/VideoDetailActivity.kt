package com.github.stormwyrm.weather

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import cn.jzvd.JzvdStd
import com.blankj.utilcode.util.BarUtils
import com.github.stormwyrm.weather.ui.theme.ComposeDemoTheme

class VideoDetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BarUtils.setStatusBarVisibility(this, false)
        val url = intent.getStringExtra(EXTRA_VIDEO_URL)!!
        val title = intent.getStringExtra(EXTRA_VIDEO_TITLE)!!
        setContent {
            ComposeDemoTheme {
                VideoDetailPage(videoUrl = url, title = title) {
                    finish()
                }
            }
        }
    }

    companion object {
        const val EXTRA_VIDEO_URL = "EXTRA_VIDEO_URL"
        const val EXTRA_VIDEO_TITLE = "EXTRA_VIDEO_TITLE"
        fun start(context: Context, videoUrl: String, videoTitle: String) {
            context.startActivity(
                Intent(
                    context,
                    VideoDetailActivity::class.java
                ).apply {
                    putExtra(EXTRA_VIDEO_URL, videoUrl)
                    putExtra(EXTRA_VIDEO_TITLE, videoTitle)

                })
            (context as? Activity)?.overridePendingTransition(
                R.anim.slide_bottom_in,
                R.anim.slide_bottom_out
            )
        }
    }
}

@Composable
fun VideoDetailPage(videoUrl: String, title: String, click: () -> Unit) {
    val localDensity = LocalDensity.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        AndroidView(
            factory = {
                JzvdStd(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        localDensity.run {
                            200.dp.toPx().toInt()
                        }
                    )
                }
            },
            modifier = Modifier.align(Alignment.Center)
        ) {
            it.setUp(videoUrl, title)
            it.startVideoAfterPreloading()
        }
        BackArrowDown {
            click()
        }
    }
}

@Composable
fun BackArrowDown(click: () -> Unit) {
    Surface(
        shape = CircleShape,
        modifier = Modifier
            .padding(15.dp, 35.dp, 0.dp, 0.dp)
            .size(24.dp),
        color = Color.Gray
    ) {
        Icon(
            Icons.Default.KeyboardArrowDown,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.clickable {
                click()
            })
    }
}