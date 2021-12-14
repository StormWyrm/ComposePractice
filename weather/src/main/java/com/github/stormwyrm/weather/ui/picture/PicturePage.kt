package com.github.stormwyrm.weather.ui.picture

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.github.stormwyrm.weather.R
import com.github.stormwyrm.weather.ui.view.LoadingPage
import com.github.stormwyrm.weather.ui.view.TitleBar
import com.github.stormwyrm.weather.viewmodel.PictureViewModel

@ExperimentalFoundationApi
@Composable
fun PicturePage(pictureViewModel: PictureViewModel = viewModel()) {
    val state by pictureViewModel.stateLiveData.observeAsState()
    val pictureList by pictureViewModel.picLiveData.observeAsState(listOf())

    LoadingPage(
        state = state!!,
        loadInit = {
            pictureViewModel.getPicList()
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            TitleBar(text = stringResource(id = R.string.pic_tab_title))
            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
                contentPadding = PaddingValues(5.dp),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
            ) {
                items(pictureList) { item ->
                    Image(
                        painter = rememberImagePainter(
                            item.url800,
                            builder = {
                                crossfade(true)
                                transformations(RoundedCornersTransformation(10f))
                            }),
                        null,
                        contentScale = ContentScale.Inside,
                        modifier = Modifier
                            .padding(5.dp)
                            .width(120.dp)
                            .height(120.dp)
                            .clickable {

                            }
                    )
                }
            }
        }
    }
}