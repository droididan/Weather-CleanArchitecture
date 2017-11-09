package com.smilebackapp.weather.weather.data.datasource.networking.json

data class Main(
        val temp: Float,
        val pressure: Float,
        val humidity: Int,
        val temp_min: Float,
        val temp_max: Float
)