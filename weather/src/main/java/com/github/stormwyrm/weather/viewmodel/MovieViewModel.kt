package com.github.stormwyrm.weather.viewmodel

import androidx.lifecycle.MutableLiveData
import com.github.stormwyrm.weather.bean.MovieItemModel
import com.github.stormwyrm.weather.http.ApiService

class MovieViewModel : BaseViewModel() {

    val moviesLiveData = MutableLiveData<List<MovieItemModel>>()

    fun getMovieLists() {
        launch {
            val movieModel = ApiService.getMovies()
            moviesLiveData.value = movieModel.itemList
        }
    }
}