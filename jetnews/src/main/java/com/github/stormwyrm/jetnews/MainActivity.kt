package com.github.stormwyrm.jetnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.stormwyrm.jetnews.ui.SwipeToRefreshLayout
import com.github.stormwyrm.jetnews.ui.theme.ComposeDemoTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                var refreshState by remember{ mutableStateOf(false) }
                LaunchedEffect(refreshState) {
                    if (refreshState) {
                        delay(2000)
                        refreshState = false
                    }
                }
                SwipeToRefreshLayout(
                    refreshingState = refreshState,
                    onRefresh = {

                    },
                    refreshIndicator = {
                        Surface(elevation = 10.dp, shape = CircleShape) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(36.dp)
                                    .padding(4.dp)
                            )
                        }
                    }) {
                    Text(
                        text = "Hello Android!",
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeDemoTheme {
        SwipeToRefreshLayout(
            refreshingState = false,
            onRefresh = { /*TODO*/ },
            refreshIndicator = {
                Surface(elevation = 10.dp, shape = CircleShape) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(36.dp)
                            .padding(4.dp)
                    )
                }
            }) {
            Text(
                text = "Hello Android!",
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            )
        }
    }
}