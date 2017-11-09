package com.smilebackapp.weather.weather.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.ajalt.timberkt.Timber
import com.smilebackapp.weather.R
import com.smilebackapp.weather.weather.presentation.model.PresentationWeather

/**
 * Created by idan on 08/11/2017.
 * Weather adapter
 */
class WeatherAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private lateinit var listener : WeatherListener

    var weatherResponse = listOf<PresentationWeather>()
        set(value) {
            field = value
            Timber.d { "data has changed: $value" }
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is WeatherCellHolder) {
            val weather = weatherResponse[position]
            holder.bind(weather)
            holder.itemView.setOnClickListener {
                listener.onWeatherClicked(weather)
            }
        }
    }

    override fun getItemCount(): Int = weatherResponse.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_weather_cell, parent, false)
        return WeatherCellHolder(view)
    }

    class WeatherCellHolder(item: View) : RecyclerView.ViewHolder(item) {


        fun bind(item: PresentationWeather) {
            TODO("Update MODEL")
//            itemView.textview_item_time.text = item.dt_txt.substring(11, 16)
//            val temp = item.main.temp
//            itemView.textview_item_temperature.text = String.format(Locale.getDefault(), "%.1f\u00B0", temp)
//            itemView.imageview_item_icon.loadImage(item.weather[0].icon)

        }
    }

    fun setListener(listener: WeatherListener) {
        this.listener = listener
    }

    fun setWeather(list: List<PresentationWeather>) {
        this.weatherResponse = list
    }

    interface WeatherListener {
        fun onWeatherClicked(item: PresentationWeather)
    }
}