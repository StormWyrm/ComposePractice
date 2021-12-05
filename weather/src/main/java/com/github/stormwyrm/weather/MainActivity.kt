package com.github.stormwyrm.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.blankj.utilcode.util.BarUtils
import com.github.stormwyrm.weather.ui.main.MainPage
import com.github.stormwyrm.weather.ui.theme.ComposeDemoTheme
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BarUtils.setStatusBarColor(this, android.graphics.Color.TRANSPARENT)
        BarUtils.setStatusBarLightMode(window, true)
        setContent {
            ComposeDemoTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MainPage()
                }
            }
        }
    }
}


