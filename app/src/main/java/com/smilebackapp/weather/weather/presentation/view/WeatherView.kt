package com.smilebackapp.weather.weather.presentation.view

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smilebackapp.weather.R
import com.smilebackapp.weather.core.presentation.BaseFragment
import com.smilebackapp.weather.core.presentation.BasePresenter
import com.smilebackapp.weather.core.presentation.BaseView
import com.smilebackapp.weather.weather.presentation.adapter.WeatherAdapter
import com.smilebackapp.weather.weather.presentation.model.PresentationWeather
import com.smilebackapp.weather.weather.presentation.presenter.WeatherPresenter
import dagger.android.support.AndroidSupportInjection
import io.reactivex.functions.Action
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlinx.android.synthetic.main.state_view_error.*
import javax.inject.Inject

/**
 * Created by idan on 06/11/2017.
 *
 */

interface WeatherView : BaseView {
    fun setWeather(list: List<PresentationWeather>)
    fun hideWeatherList()
}

class WeatherFragment : BaseFragment(), WeatherView, SwipeRefreshLayout.OnRefreshListener, WeatherAdapter.WeatherListener {

    @Inject lateinit var presenter: WeatherPresenter

    @Inject lateinit var weatherAdapter: WeatherAdapter

    companion object {
        fun get(): WeatherFragment {
            return WeatherFragment()
        }
    }

    override fun hideWeatherList() {
        recyclerView.visibility = View.GONE
    }

    override fun setWeather(list: List<PresentationWeather>) {
        weatherAdapter.setWeather(list)
    }

    override fun onWeatherClicked(item: PresentationWeather) {
        presenter.navigateWeatherDetails()
    }

    override fun onResume() {
        super.onResume()
        onRefresh()
    }

    override fun onRefresh() {
        swiperefreshlayout.isRefreshing = false
        presenter.getWeather()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_weather, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectDependencies()
        setupView()
    }

    private fun setupView() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = weatherAdapter
        weatherAdapter.setListener(this)
        swiperefreshlayout.setOnRefreshListener(this)
        buttonTryAgain.setOnClickListener { onRefresh() }
    }

    override fun getPresenter(): BasePresenter = presenter

    override fun injectDependencies() {
        AndroidSupportInjection.inject(this)
    }

    override fun enableRefresh(): Action = Action { swiperefreshlayout.isEnabled = true }

    override fun disableRefresh(): Action = Action { swiperefreshlayout.isEnabled = false }
}