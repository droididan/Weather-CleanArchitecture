package com.smilebackapp.weather.weather.presentation.mapper

import com.smilebackapp.weather.core.mapper.BaseMapper
import com.smilebackapp.weather.weather.data.datasource.networking.json.WeatherResponseItem
import com.smilebackapp.weather.weather.domain.model.Weather
import com.smilebackapp.weather.weather.presentation.model.PresentationWeather

/**
 * Created by idan on 08/11/2017.
 */
object WeatherMapper : BaseMapper<WeatherResponseItem, PresentationWeather>(){
    override fun transformTo(source: WeatherResponseItem): PresentationWeather = PresentationWeather(dt = source.dt, name = source.name, wind = source.wind)

    override fun transformFrom(source: PresentationWeather): WeatherResponseItem {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}