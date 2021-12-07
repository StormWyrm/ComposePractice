package com.github.stormwyrm.weather

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import com.github.stormwyrm.weather.ui.theme.ComposeDemoTheme
import com.github.stormwyrm.weather.ui.view.ProgressWebView
import com.github.stormwyrm.weather.ui.view.TitleBar
import com.google.accompanist.insets.ProvideWindowInsets

class NewsDetailActivity : ComponentActivity() {

    private val webView: ProgressWebView by lazy {
        ProgressWebView(this).apply {
            settings.useWideViewPort = true
            settings.loadWithOverviewMode = true
            webViewClient = WebViewClient()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT

        val title = intent.getStringExtra(EXTRA_TITLE)!!
        val url = intent.getStringExtra(EXTRA_URL)!!
        setContent {
            ComposeDemoTheme {
                ProvideWindowInsets {
                    NewsDetailPage(title = title, url = url, webView = webView, onBackClick = {
                        onBackPressed()
                    })
                }
            }
        }

    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
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
fun NewsDetailPage(title: String, url: String, webView: WebView, onBackClick: () -> Unit) {

    Column(modifier = Modifier.fillMaxSize()) {
        TitleBar(text = title, true, onBackClick)
        AndroidView(factory = {
            webView
        }) {
            it.loadUrl(url)
        }
    }
}