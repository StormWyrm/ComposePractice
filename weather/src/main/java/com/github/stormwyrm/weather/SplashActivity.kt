package com.github.stormwyrm.weather

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.SPUtils
import com.github.stormwyrm.weather.contants.SP_IS_GUIDE_PAGE
import com.github.stormwyrm.weather.ui.splash.SplashPage
import com.github.stormwyrm.weather.ui.theme.ComposeDemoTheme
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BarUtils.setStatusBarColor(this,Color.TRANSPARENT)
        BarUtils.setStatusBarLightMode(window,true)
        setContent {
            ComposeDemoTheme {
                SplashPage {
                    val intent = if (SPUtils.getInstance().getBoolean(SP_IS_GUIDE_PAGE)) {
                        Intent(this, MainActivity::class.java)
                    } else {
                        Intent(this, GuideActivity::class.java)
                    }
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}