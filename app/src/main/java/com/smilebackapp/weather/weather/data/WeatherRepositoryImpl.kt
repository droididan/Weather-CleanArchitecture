package com.smilebackapp.weather.weather.data

import com.smilebackapp.weather.weather.data.datasource.networking.WeatherNetworkingDataSource
import com.smilebackapp.weather.weather.domain.model.Weather
import com.smilebackapp.weather.weather.domain.repository.WeatherRepository
import io.reactivex.Flowable

/**
 * Created by idan on 06/11/2017.
 *
 */
class WeatherRepositoryImpl(private val networking: WeatherNetworkingDataSource) : WeatherRepository {

    override fun getWeather(cityName: String): Flowable<Weather> =
            networking.getWeather(cityName)
}