package com.github.stormwryrm.composecustomview.ui.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy

@Composable
fun CustomColumn(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
   Layout(content = content, measurePolicy = object : MeasurePolicy)
}