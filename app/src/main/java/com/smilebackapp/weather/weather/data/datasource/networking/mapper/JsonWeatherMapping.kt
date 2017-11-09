package com.smilebackapp.weather.weather.data.datasource.networking.mapper

import com.smilebackapp.weather.core.mapper.BaseMapper
import com.smilebackapp.weather.weather.data.datasource.networking.json.JsonWeatherResponse
import com.smilebackapp.weather.weather.domain.model.Weather

/**
 * Created by idan on 09/11/2017.
 * Mapper
 */
class JsonWeatherMapping : BaseMapper<JsonWeatherResponse, Weather>() {

    override fun transformFrom(source: Weather): JsonWeatherResponse = TODO("not implemented")

    override fun transformTo(source: JsonWeatherResponse): Weather = Weather(list = source.list, city = source.city)
}