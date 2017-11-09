package com.smilebackapp.weather.weather.data.datasource.networking

import com.smilebackapp.weather.weather.domain.model.Weather
import io.reactivex.Flowable

/**
 * Created by idan on 09/11/2017.
 * Network Data Source
 */
interface WeatherNetworkingDataSource {
    fun getWeather(cityNameAndCountry: String): Flowable<Weather>
}

class WeatherNetworkingDataSourceImpl(private val api: WeatherApi) : WeatherNetworkingDataSource {

    // return a Flowable with the weather response
    override fun getWeather(cityNameAndCountry: String): Flowable<Weather> =
            api.getFireDayForecast(cityNameAndCountry)
                    .map { Weather(it.list, it.city) }
                    .toFlowable()
}