package com.github.stormwyrm.weather.viewmodel

import androidx.lifecycle.MutableLiveData
import com.github.stormwyrm.weather.bean.NewsModelModel
import com.github.stormwyrm.weather.bean.StoryModel
import com.github.stormwyrm.weather.http.ApiService

class NewsViewModel : BaseViewModel() {

    val newsLiveData = MutableLiveData<NewsModelModel>()

    fun getNewsLists() {
        launch {
            val newsModel = ApiService.getNews()
            newsLiveData.value = newsModel
        }
    }
}