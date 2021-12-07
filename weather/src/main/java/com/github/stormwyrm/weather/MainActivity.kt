package com.github.stormwyrm.weather

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.core.view.WindowCompat
import com.github.stormwyrm.weather.ui.main.MainPage
import com.github.stormwyrm.weather.ui.theme.ComposeDemoTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        window.statusBarColor = Color.TRANSPARENT
        setContent {
            ComposeDemoTheme {
                ProvideWindowInsets() {
                    Surface(color = MaterialTheme.colors.background) {
                        MainPage()
                    }
                }
            }
        }
    }
}


