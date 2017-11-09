package com.smilebackapp.weather.weather.data.datasource.networking

import com.smilebackapp.weather.BuildConfig
import com.smilebackapp.weather.weather.data.datasource.networking.json.JsonWeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by idan on 08/11/2017.
 */
interface WeatherApi {

    @GET("forecast?unit=metric")
    fun getFireDayForecast(@Query("q") cityNameAndCountry: String, @Query("appid") apiKey: String = BuildConfig.API_KEY): Single<JsonWeatherResponse>


}