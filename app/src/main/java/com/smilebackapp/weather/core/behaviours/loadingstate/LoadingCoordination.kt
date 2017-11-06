package com.smilebackapp.weather.core.behaviours.loadingstate

import com.smilebackapp.weather.core.behaviours.ShowAtStartHideWhenDone
import com.smilebackapp.weather.core.presentation.LoadingView
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.Scheduler
import org.reactivestreams.Publisher

class LoadingCoordination<T>(private val view: LoadingView,
                             private val uiScheduler: Scheduler) : FlowableTransformer<T, T> {

    override fun apply(upstream: Flowable<T>): Publisher<T> {

        val delegate = ShowAtStartHideWhenDone<T>(
                view.showLoading(),
                view.hideLoading(),
                uiScheduler
        )

        return upstream.compose(delegate)
    }

}
