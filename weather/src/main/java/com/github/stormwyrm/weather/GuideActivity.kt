package com.github.stormwyrm.weather

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.SPUtils
import com.github.stormwyrm.weather.contants.SP_IS_GUIDE_PAGE
import com.github.stormwyrm.weather.ui.guide.GuidePage
import com.github.stormwyrm.weather.ui.theme.ComposeDemoTheme
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
class GuideActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT)
        BarUtils.setStatusBarLightMode(window,true)
        setContent {
            ComposeDemoTheme {
                GuidePage(
                    images = arrayListOf(
                        R.drawable.guide_1,
                        R.drawable.guide_2,
                        R.drawable.guide_3
                    )
                ) {
                    SPUtils.getInstance().put(SP_IS_GUIDE_PAGE, true)
                    go2Main()
                }
            }
        }
    }

    private fun go2Main() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}