package com.smilebackapp.weather.core.domain

interface UseCase<out T> {
    fun execute(): T
}