package com.smilebackapp.weather.weather.data

import com.smilebackapp.weather.core.networking.errors.NetworkingErrorHandler
import com.smilebackapp.weather.core.networking.errors.RestErrorsHandler
import com.smilebackapp.weather.weather.data.datasource.networking.WeatherNetworkingDataSource
import com.smilebackapp.weather.weather.data.datasource.networking.json.WeatherResponse
import com.smilebackapp.weather.weather.data.datasource.networking.json.WeatherResponseItem
import com.smilebackapp.weather.weather.domain.repository.WeatherRepository
import io.reactivex.Flowable

/**
 * Created by idan on 06/11/2017.
 *
 */
class WeatherRepositoryImpl(private val networking: WeatherNetworkingDataSource) : WeatherRepository {
    override fun getWeather(cityName: String): Flowable<WeatherResponse> =
            networking.getWeather(cityName)
                    .compose(NetworkingErrorHandler<List<WeatherResponseItem>>)
                    .compose(RestErrorsHandler<List<WeatherResponseItem>>)

}