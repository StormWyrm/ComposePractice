package com.github.stormwyrm.weather.viewmodel

import androidx.lifecycle.MutableLiveData
import com.github.stormwyrm.weather.bean.WeatherModel
import com.github.stormwyrm.weather.http.ApiService
import com.github.stormwyrm.weather.ui.weather.WeatherImageFactory

class WeatherViewModel : BaseViewModel() {

    val weatherLiveData = MutableLiveData<List<WeatherModel>>()

    fun getWeathers() {
        launch {
            val weatherResponse = ApiService.getWeathers()
            weatherResponse.result.forEach {
                it.imgRes = WeatherImageFactory.getWeatherImage(it.weather)
            }
            weatherLiveData.value = weatherResponse.result
        }
    }

}