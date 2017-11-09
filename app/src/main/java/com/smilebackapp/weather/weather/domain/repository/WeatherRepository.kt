package com.smilebackapp.weather.weather.domain.repository

import com.smilebackapp.weather.weather.domain.model.Weather
import io.reactivex.Flowable

/**
 * Created by idan on 08/11/2017.
 *
 */
interface WeatherRepository {
    fun getWeather(cityName: String): Flowable<Weather>
}