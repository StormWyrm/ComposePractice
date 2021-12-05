package com.github.stormwyrm.weather.ui.weather

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun WeatherPage() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Weather",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}