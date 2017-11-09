package com.smilebackapp.weather.weather.presentation.model

import com.smilebackapp.weather.weather.data.datasource.networking.json.Wind

/**
 * Created by idan on 08/11/2017.
 * Model to show
 */
data class PresentationWeather(val dt: Long, val name: String, val wind: Wind)