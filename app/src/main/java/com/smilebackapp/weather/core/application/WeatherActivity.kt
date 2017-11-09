package com.smilebackapp.weather.core.application

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.smilebackapp.weather.R
import com.smilebackapp.weather.core.performTransaction
import com.smilebackapp.weather.weather.presentation.view.WeatherFragment

class WeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.performTransaction {
            replace(R.id.container, WeatherFragment.get())
        }
    }
}

