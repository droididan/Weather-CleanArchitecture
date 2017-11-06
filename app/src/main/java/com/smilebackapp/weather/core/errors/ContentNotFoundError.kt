package com.smilebackapp.weather.core.errors

class ContentNotFoundError : RuntimeException() {
    override val message: String?
        get() = "No content available"
}
