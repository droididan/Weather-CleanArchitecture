package com.smilebackapp.weather.weather.domain.usecase

import com.smilebackapp.weather.core.domain.UseCase
import com.smilebackapp.weather.weather.data.datasource.networking.json.WeatherResponse
import com.smilebackapp.weather.weather.data.datasource.networking.json.WeatherResponseItem
import com.smilebackapp.weather.weather.domain.repository.WeatherRepository
import io.reactivex.Flowable

interface GetWeatherUseCase : UseCase<Flowable<WeatherResponse>> {
    fun setField(country:String) : GetWeatherUseCase
}

class GetWeatherUseCaseImpl(private val repository: WeatherRepository) : GetWeatherUseCase {

    private lateinit var countryName: String

    override fun setField(country: String) : GetWeatherUseCase {
        this.countryName = country
        return this
    }

    override fun execute(): Flowable<WeatherResponse> = repository.getWeather(countryName)
}