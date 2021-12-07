package com.github.stormwyrm.weather

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.github.stormwyrm.weather.ui.theme.ComposeDemoTheme
import com.github.stormwyrm.weather.ui.view.ProgressWebView
import com.github.stormwyrm.weather.ui.view.TitleBar

class NewsDetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val title = intent.getStringExtra(EXTRA_TITLE)!!
        val url = intent.getStringExtra(EXTRA_URL) !!

        setContent {
            ComposeDemoTheme {
                NewsDetailPage(title = title,url = url,onBackClick = {

                })
            }
        }
    }

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_URL = "extra_url"

        fun start(context: Context, title: String, url: String) {
            Intent(context, NewsDetailActivity::class.java).also {
                it.putExtra(EXTRA_TITLE, title)
                it.putExtra(EXTRA_URL, url)
                context.startActivity(it)
            }
        }
    }
}

@Composable
fun NewsDetailPage(title: String, url: String, onBackClick: () -> Unit) {

    Column(modifier = Modifier.fillMaxSize()) {
        TitleBar(text = title, true, onBackClick)
        AndroidView(factory = {
            ProgressWebView(it)
        }) {
            it.loadUrl(url)
        }
    }
}