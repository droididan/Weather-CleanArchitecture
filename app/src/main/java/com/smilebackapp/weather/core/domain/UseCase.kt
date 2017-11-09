package com.smilebackapp.weather.core.domain

import com.smilebackapp.weather.weather.data.datasource.networking.json.WeatherResponse
import io.reactivex.Flowable

interface UseCase<out T> {
    fun execute(): Flowable<WeatherResponse>
}