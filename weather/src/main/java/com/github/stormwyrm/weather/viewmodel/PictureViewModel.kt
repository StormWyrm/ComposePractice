package com.github.stormwyrm.weather.viewmodel

import androidx.lifecycle.MutableLiveData
import com.github.stormwyrm.weather.bean.PictureModel
import com.github.stormwyrm.weather.http.ApiService

class PictureViewModel : BaseViewModel() {

    val picLiveData = MutableLiveData<List<PictureModel>>()

    fun getPicList() {
        launch {
            val pics = ApiService.getPics().list
            pics.forEach {
                it.url800 = "https:${it.url800}"
            }
            picLiveData.value = pics
        }
    }

}