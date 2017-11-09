package com.smilebackapp.weather.weather.di

import android.arch.lifecycle.LifecycleOwner
import com.smilebackapp.weather.core.application.di.IOScheduler
import com.smilebackapp.weather.core.application.di.UIScheduler
import com.smilebackapp.weather.core.behaviours.BehavioursCoordinator
import com.smilebackapp.weather.core.behaviours.di.BehavioursModule
import com.smilebackapp.weather.core.presentation.*
import com.smilebackapp.weather.core.presentation.lifecycles.LifecycleStrategist
import com.smilebackapp.weather.core.presentation.lifecycles.di.LifecycleStrategistModule
import com.smilebackapp.weather.weather.data.WeatherRepositoryImpl
import com.smilebackapp.weather.weather.data.datasource.networking.WeatherApi
import com.smilebackapp.weather.weather.data.datasource.networking.WeatherNetworkingDataSource
import com.smilebackapp.weather.weather.data.datasource.networking.WeatherNetworkingDataSourceImpl
import com.smilebackapp.weather.weather.domain.model.Weather
import com.smilebackapp.weather.weather.domain.repository.WeatherRepository
import com.smilebackapp.weather.weather.domain.usecase.GetWeatherUseCase
import com.smilebackapp.weather.weather.domain.usecase.GetWeatherUseCaseImpl
import com.smilebackapp.weather.weather.presentation.adapter.WeatherAdapter
import com.smilebackapp.weather.weather.presentation.navigation.WeatherNavigation
import com.smilebackapp.weather.weather.presentation.presenter.WeatherPresenter
import com.smilebackapp.weather.weather.presentation.presenter.WeatherPresenterImpl
import com.smilebackapp.weather.weather.presentation.view.WeatherFragment
import com.smilebackapp.weather.weather.presentation.view.WeatherView
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlinx.android.synthetic.main.state_view_empty.*
import kotlinx.android.synthetic.main.state_view_error.*
import kotlinx.android.synthetic.main.state_view_loading.*
import retrofit2.Retrofit
import javax.inject.Named

/**
 * Created by idan on 06/11/2017.
 *
 */
@Module(includes = arrayOf(
        BehavioursModule::class,
        LifecycleStrategistModule::class,
        WeatherUseCaseModel::class))

class WeatherModule {

    @Provides fun providesWeatherPresenter(weatherUseCase: GetWeatherUseCase,
                                           coordinator: BehavioursCoordinator<Weather>,
                                           strategist: LifecycleStrategist,
                                           weatherView: WeatherView,
                                           @IOScheduler scheduler: Scheduler,
                                           @UIScheduler uiScheduler: Scheduler,
                                           navigation: WeatherNavigation)
            : WeatherPresenter = WeatherPresenterImpl(
            getWeatherUseCase = weatherUseCase,
            coordinator = coordinator,
            strategist = strategist,
            view = weatherView,
            ioScheduler = scheduler,
            uiScheduler = uiScheduler,
            navigation = navigation)

    // Provide Lifecycle
    @Provides fun provideStrategist(view: WeatherFragment): LifecycleOwner = view

    // Provide Adapter
    @Provides fun provideWeatherAdapter(): WeatherAdapter = WeatherAdapter()

    @Provides fun providesWeatherRepository(networkingDataSource: WeatherNetworkingDataSource) : WeatherRepository = WeatherRepositoryImpl(networkingDataSource)

    @Provides fun providesNetworkDataSource(api: WeatherApi) : WeatherNetworkingDataSource = WeatherNetworkingDataSourceImpl(api = api)

    // Provide View States
    @Provides fun providesEmptyStateView(view: WeatherFragment): EmptyStateView = view

    @Provides fun providesLoadingView(view: WeatherFragment): LoadingView = view

    @Provides fun providesErrorView(view: WeatherFragment): ErrorStateView = view

    @Provides fun providesToogleRefreshView(view: WeatherFragment): ToogleRefreshView = view

    @Provides fun providesNetworkingView(view: WeatherFragment): NetworkingView = view

    // Provide Views Placeholder
    @Provides fun providesPlaceHolder(activity: WeatherFragment):
            PlaceholderViewsManager = PlaceholderViewsManager(
            loadingViewStub = activity.state_view_loading,
            errorViewStub = activity.state_view_error,
            emptyViewStub = activity.state_view_empty,
            containerView = activity.container)
}

@Module
class WeatherUseCaseModel {

    // Provide UseCase Communications
    @Provides fun providesTransactionApi(@Named("retrofitDefault") retrofit: Retrofit): WeatherApi = retrofit.create(WeatherApi::class.java)

    // UseCases
    @Provides fun provideGetWeather(weatherRepository: WeatherRepository): GetWeatherUseCase = GetWeatherUseCaseImpl(weatherRepository)


}