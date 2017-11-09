package com.smilebackapp.weather.weather.domain.usecase

import com.smilebackapp.weather.core.domain.UseCase
import com.smilebackapp.weather.weather.domain.model.Weather
import com.smilebackapp.weather.weather.domain.repository.WeatherRepository
import io.reactivex.Flowable

interface GetWeatherUseCase : UseCase<Flowable<Weather>> {
    fun setField(country:String) : GetWeatherUseCase
}

class GetWeatherUseCaseImpl(private val repository: WeatherRepository) : GetWeatherUseCase {

    private lateinit var countryName: String

    override fun setField(country: String) : GetWeatherUseCase {
        this.countryName = country
        return this
    }

    override fun execute(): Flowable<Weather> = repository.getWeather(countryName)
}