package com.smilebackapp.weather.weather.data.datasource.networking.json


data class WeatherResponse(
        val cod: Int,
        val message: Float,
        val cnt: Int,
        val list: List<WeatherResponseItem>,
        val city: City
)