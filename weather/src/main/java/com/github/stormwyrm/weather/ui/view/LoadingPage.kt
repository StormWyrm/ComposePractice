package com.github.stormwyrm.weather.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.stormwyrm.weather.R
import com.github.stormwyrm.weather.common.State

@Composable
fun LoadingPage(
    state: State = State.Loading,
    loadInit: (() -> Unit)? = null,
    contentView: @Composable BoxScope.() -> Unit
) {

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when {
            state.isLoading() -> {
                CircularProgressIndicator()
                loadInit?.invoke()
            }
            state.isError() -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(20.dp)
                        .clickable {
                            loadInit?.invoke()
                        }
                ) {
                    Image(painterResource(R.drawable.ic_no_network), null, Modifier.size(80.dp))
                    Text(
                        text = "${(state as State.Error).msg.toString()}",
                        textAlign = TextAlign.Center
                    )
                }
            }
            else -> {
                contentView()
            }
        }
    }
}

