package com.github.stormwyrm.weather.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import coil.annotation.ExperimentalCoilApi
import com.github.stormwyrm.weather.R
import com.github.stormwyrm.weather.ui.movie.MoviePage
import com.github.stormwyrm.weather.ui.news.NewsPage
import com.github.stormwyrm.weather.ui.picture.PicturePage
import com.github.stormwyrm.weather.ui.weather.WeatherPage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@ExperimentalCoilApi
@ExperimentalPagerApi
@Composable
fun MainPage() {
    Column {
        val pagerState = rememberPagerState(
            pageCount = 4,
            initialOffscreenLimit = 4
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            when (page) {
                0 -> NewsPage()
                1 -> MoviePage()
                2 -> PicturePage()
                3 -> WeatherPage()
            }
        }

        BottomNavigationComponent(pagerState = pagerState)

    }
}

@ExperimentalPagerApi
@Composable
private fun BottomNavigationComponent(pagerState: PagerState) {
    val context = LocalContext.current

    val listItems = listOf(
        context.resources.getString(R.string.news_tab_title),
        context.resources.getString(R.string.video_tab_title),
        context.resources.getString(R.string.pic_tab_title),
        context.resources.getString(R.string.weather_tab_title)
    )

    val scope = rememberCoroutineScope()

    val curPage = pagerState.currentPage

    BottomNavigation(backgroundColor = Color.White) {
        listItems.forEachIndexed { index, label ->
            BottomNavigationItem(
                icon = {
                    when (index) {
                        0 -> BottomIcon(Icons.Filled.Home, curPage, index)
                        1 -> BottomIcon(Icons.Filled.List, curPage, index)
                        2 -> BottomIcon(Icons.Filled.Favorite, curPage, index)
                        3 -> BottomIcon(Icons.Filled.ThumbUp, curPage, index)
                    }
                },
                label = {
                    Text(
                        label,
                        color = if (curPage == index)
                            MaterialTheme.colors.primary
                        else
                            Color.Gray
                    )
                },
                selected = curPage == index,
                onClick = {
                    scope.launch {
                        pagerState.scrollToPage(index)
                    }
                }
            )

        }
    }
}

@Composable
private fun BottomIcon(imageVector: ImageVector, selectedIndex: Int, index: Int) {
    Icon(
        imageVector,
        null,
        tint = if (selectedIndex == index) MaterialTheme.colors.primary else Color.Gray
    )
}