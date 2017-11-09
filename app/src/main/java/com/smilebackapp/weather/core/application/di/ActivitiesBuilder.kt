package com.smilebackapp.weather.core.application.di

import com.smilebackapp.weather.weather.di.WeatherModule
import com.smilebackapp.weather.weather.presentation.view.WeatherFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesBuilder {
    /**
     * Add here the the module and the target [Fragment / Activity]
     */

    @ContributesAndroidInjector(modules = arrayOf(WeatherModule::class))
    abstract fun weatherFragment(): WeatherFragment

}
