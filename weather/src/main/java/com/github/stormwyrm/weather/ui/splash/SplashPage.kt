package com.github.stormwyrm.weather.ui.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.BarUtils
import com.github.stormwyrm.weather.R
import kotlinx.coroutines.delay

private const val MAX_TIME = 3

@SuppressLint("RememberReturnType")
@Composable
fun SplashPage(
    onSplashFinished: (() -> Unit)? = null
) {
    var time by remember {
        mutableStateOf(MAX_TIME)
    }

    LaunchedEffect(key1 = Unit) {

        while (time > 0) {
            delay(1000L)
            time--
        }
        onSplashFinished?.invoke()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(Modifier.align(Alignment.Center)) {
            Image(painter = painterResource(id = R.mipmap.ic_launcher), contentDescription = null)
            Text(text = stringResource(id = R.string.app_name))
        }

        Text(
            text = "$time s",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(
                    top = LocalDensity.current.run {
                        BarUtils
                            .getStatusBarHeight()
                            .toDp()
                    },
                    end = 10.dp
                )
                .clip(RoundedCornerShape(4.dp))
        )

        Text(
            text = "v ${AppUtils.getAppVersionName()}",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(10.dp)
        )
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashPagePreview() {
    SplashPage()
}