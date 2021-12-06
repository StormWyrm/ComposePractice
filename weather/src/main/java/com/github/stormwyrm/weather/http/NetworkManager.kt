package com.github.stormwyrm.weather.http

import android.util.Log
import com.github.stormwyrm.weather.BuildConfig
import com.github.stormwyrm.weather.api.Api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val ZHIHU_URL = "http://news-at.zhihu.com/api/"

private val TAG = "NetworkManager"

private val okHttpClient by lazy {
    OkHttpClient.Builder()
        .apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(
                    HttpLoggingInterceptor { message -> Log.e(TAG, message) }
                        .setLevel(level = HttpLoggingInterceptor.Level.BODY)
                )
            }
        }.build()
}

private val retrofit by lazy {
    Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(
            ZHIHU_URL
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

object ApiService : Api by retrofit.create(
    Api::class.java
)
