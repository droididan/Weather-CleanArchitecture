package com.smilebackapp.weather.weather.domain.model

import com.smilebackapp.weather.weather.data.datasource.networking.json.City
import com.smilebackapp.weather.weather.data.datasource.networking.json.WeatherResponseItem

/**
 * Created by idan on 09/11/2017.
 */
class Weather(
        val list: List<WeatherResponseItem>,
        val city: City
)