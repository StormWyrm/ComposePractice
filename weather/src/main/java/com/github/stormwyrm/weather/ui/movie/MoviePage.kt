package com.github.stormwyrm.weather.ui.movie

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.blankj.utilcode.util.TimeUtils
import com.github.stormwyrm.weather.R
import com.github.stormwyrm.weather.bean.MovieItem
import com.github.stormwyrm.weather.bean.MovieItemModel
import com.github.stormwyrm.weather.ui.view.LoadingPage
import com.github.stormwyrm.weather.ui.view.TitleBar
import com.github.stormwyrm.weather.viewmodel.MovieViewModel

@ExperimentalCoilApi
@Composable
fun MoviePage(movieViewModel: MovieViewModel = viewModel()) {
    val videoModel by movieViewModel.moviesLiveData.observeAsState()
    val state by movieViewModel.stateLiveData.observeAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        TitleBar(text = stringResource(id = R.string.recommend_movie_title))
        LoadingPage(
            state = state!!,
            loadInit = { movieViewModel.getMovieLists() }
        ) {
            LazyColumn {
                items(videoModel?.chunked(2) ?: mutableListOf()) { item ->
                    MovieRow(movieItems = item)
                }
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun MovieRow(movieItems: List<MovieItemModel>) {
    if (movieItems.isEmpty())
        return
    val first = movieItems.first().data
    var second: MovieItem? = null
    if (movieItems.size > 1) {
        second = movieItems.last().data
    }
    Row {
        if (second != null) {
            MovieItem(movieItem = first, modifier = Modifier.weight(1f))
            MovieItem(movieItem = second, modifier = Modifier.weight(1f))
        } else {
            MovieItem(movieItem = first, modifier = Modifier.fillMaxWidth(0.5f))
        }
    }
}

@ExperimentalCoilApi
@Composable
fun MovieItem(movieItem: MovieItem, modifier: Modifier = Modifier) {
    Card(
        modifier
            .padding(5.dp)
    ) {
        Column {
            Box {
                Image(
                    painter = rememberImagePainter(data = movieItem.cover.feed),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(130.dp)
                        .clip(RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp))
                )

                Text(
                    text = TimeUtils.getFriendlyTimeSpanByNow((movieItem.duration * 1000).toLong()),
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(5.dp)
                )
            }

            Text(
                text = movieItem.title,
                maxLines = 2,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Clip,
                modifier = Modifier.padding(4.dp)
            )

            Text(
                text = movieItem.category,
                fontSize = 13.sp,
                color = Color.Gray,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}