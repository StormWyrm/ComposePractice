package com.github.stormwyrm.weather.bean

class WeatherResponse(val success: String, val result: List<WeatherModel>)

class WeatherModel(
    val week: String,
    val citynm: String,
    val weather: String,
    val temperature: String,
    val wind: String,
    val days: String,
    var imgRes: Int
)