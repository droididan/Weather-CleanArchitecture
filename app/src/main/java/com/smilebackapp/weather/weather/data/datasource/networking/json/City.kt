package com.smilebackapp.weather.weather.data.datasource.networking.json

data class City(
        val id: Int,
        val name: String,
        val coord: Coord,
        val country: String
)