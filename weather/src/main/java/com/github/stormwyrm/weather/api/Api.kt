package com.github.stormwyrm.weather.api

import com.github.stormwyrm.weather.bean.NewsModelModel
import retrofit2.http.GET

interface Api {
    companion object {
        //API均来自网上开放Api接口，仅供交流学习，切勿商用
        const val NEWS_URL = "4/news/latest"
        const val MOVIE_URL =
            "http://baobab.kaiyanapp.com/api/v4/rankList/videos"
        const val PIC_URL = "https://www.vcg.com/api/creative/sets/520055525?page=1"
        const val WEATHER_URL =
            "http://api.k780.com/?app=weather.future&weaId=169&&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json"
    }

    @GET(NEWS_URL)
    suspend fun getNews(): NewsModelModel
}