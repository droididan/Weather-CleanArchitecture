package com.smilebackapp.weather.weather.data.datasource.networking

import com.smilebackapp.weather.weather.data.datasource.networking.json.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by idan on 08/11/2017.
 */
interface WeatherApi {

    @GET("forecast?unit=metric")
    fun getFireDayForecast(@Query("q") cityNameAndCountry: String, @Query("appid") apiKey: String = "Default"): Single<WeatherResponse>


}