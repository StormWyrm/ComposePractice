package com.github.stormwyrm.weather.ui.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.github.stormwyrm.weather.NewsDetailActivity
import com.github.stormwyrm.weather.R
import com.github.stormwyrm.weather.bean.NewsModelModel
import com.github.stormwyrm.weather.bean.StoryModel
import com.github.stormwyrm.weather.bean.TopStoryModel
import com.github.stormwyrm.weather.ui.view.LoadingPage
import com.github.stormwyrm.weather.ui.view.TitleBar
import com.github.stormwyrm.weather.viewmodel.NewsViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@ExperimentalCoilApi
@ExperimentalPagerApi
@Composable
fun NewsPage(viewMode: NewsViewModel = viewModel()) {
    val newsModel by viewMode.newsLiveData.observeAsState(NewsModelModel())
    val state by viewMode.stateLiveData.observeAsState()

    Column(Modifier.fillMaxSize()) {
        TitleBar(stringResource(id = R.string.information_title))
        LoadingPage(
            state = state!!,
            loadInit = {
                viewMode.getNewsLists()
            }
        ) {
            LazyColumn {
                item {
                    NewsBanner(topStories = newsModel.top_stories)
                }
                val stories = newsModel.stories

                itemsIndexed(stories) { index, item ->
                    NewsItem(item)
                    if (index != stories.size) {
                        Divider(
                            thickness = 0.5.dp,
                            modifier = Modifier.padding(8.dp, 0.dp)
                        )
                    }
                }
            }

        }
    }

}

@ExperimentalPagerApi
@Composable
fun NewsBanner(topStories: List<TopStoryModel>) {
    val context = LocalContext.current

    Box(modifier = Modifier.height(200.dp)) {

        val pagerState = rememberPagerState(
            pageCount = topStories.size,
            infiniteLoop = true
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Image(
                painter = rememberImagePainter(data = topStories[page].image ?: "", builder = {
                    crossfade(true)
                }),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        NewsDetailActivity.start(
                            context = context,
                            title = topStories[page].title,
                            url = topStories[page].url
                        )
                    }
            )
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .padding(6.dp)
                .align(Alignment.BottomCenter),
            activeColor = MaterialTheme.colors.primary,
            inactiveColor = Color.White
        )
    }

}

@ExperimentalCoilApi
@Composable
private fun NewsItem(model: StoryModel) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                NewsDetailActivity.start(context = context, title = model.title, url = model.url)
            }
            .padding(10.dp)
    ) {

        Image(
            painter = rememberImagePainter(data = model.images[0], builder = {
                crossfade(true)
            }),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp, 80.dp)
                .clip(RoundedCornerShape(2.dp)),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        ) {
            Text(
                model.title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp, 3.dp, 0.dp, 0.dp)
            )
            Text(
                model.hint,
                fontSize = 13.sp,
                color = Color.Gray,
                modifier = Modifier.padding(10.dp, 5.dp, 0.dp, 0.dp)
            )
        }
    }
}