package com.smilebackapp.weather.weather.presentation.presenter

import com.smilebackapp.weather.core.behaviours.BehavioursCoordinator
import com.smilebackapp.weather.core.presentation.BasePresenter
import com.smilebackapp.weather.core.presentation.lifecycles.LifecycleStrategist
import com.smilebackapp.weather.weather.data.datasource.networking.json.WeatherResponse
import com.smilebackapp.weather.weather.data.datasource.networking.json.WeatherResponseItem
import com.smilebackapp.weather.weather.domain.usecase.GetWeatherUseCase
import com.smilebackapp.weather.weather.presentation.mapper.WeatherMapper
import com.smilebackapp.weather.weather.presentation.model.PresentationWeather
import com.smilebackapp.weather.weather.presentation.navigation.WeatherNavigationImpl
import com.smilebackapp.weather.weather.presentation.view.WeatherView
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by idan on 08/11/2017.
 * Weather presenter
 */

interface WeatherPresenter : BasePresenter {
    fun getWeather()
    fun navigateWeatherDetails()
}

class WeatherPresenterImpl(
        private val getWeatherUseCase: GetWeatherUseCase,
        private val coordinator: BehavioursCoordinator<Any>,
        private val strategist: LifecycleStrategist,
        private val view: WeatherView,
        private val navigation: WeatherNavigationImpl,
        private val ioScheduler: Scheduler,
        private val uiScheduler: Scheduler) : WeatherPresenter {

    override fun navigateWeatherDetails() {
        navigation.gotToWeatherDetails()
    }

    private var weatherList: MutableList<PresentationWeather> = mutableListOf()

    override fun getWeather() {
        if(weatherList.isEmpty()) {
            val getWeather = getWeatherUseCase.setField("Israel").execute()
                    .compose(coordinator as BehavioursCoordinator<>)
                    .subscribeOn(ioScheduler)
//                    .map { it.list }
                    .observeOn(uiScheduler)
                    .subscribeBy (onError = {})

            strategist.applyStrategy(getWeather)
        } else {
            view.setWeather(weatherList)
        }
    }
}