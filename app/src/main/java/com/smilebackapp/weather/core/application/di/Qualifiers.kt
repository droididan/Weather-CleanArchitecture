package com.smilebackapp.weather.core.application.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class IOScheduler

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class UIScheduler

