package com.github.stormwyrm.weather.ui.news

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun NewsPage() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "News",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}