package com.smilebackapp.weather.weather.presentation.navigation

import android.content.Context

/**
 * Created by idan on 09/11/2017.
 */
interface WeatherNavigation {
    fun gotToWeatherDetails()
}
class WeatherNavigationImpl(context: Context) : WeatherNavigation {
    override fun gotToWeatherDetails() {
         // Load DetailWeather
    }
}