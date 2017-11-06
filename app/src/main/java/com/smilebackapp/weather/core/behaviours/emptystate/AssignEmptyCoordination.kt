package com.smilebackapp.weather.core.behaviours.emptystate

import com.smilebackapp.weather.core.behaviours.HideAtStartShowAtError
import com.smilebackapp.weather.core.errors.ContentNotFoundError
import com.smilebackapp.weather.core.presentation.EmptyStateView
import com.uzias.starwarsshop.util.ErrorPredicate
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.Scheduler
import org.reactivestreams.Publisher

class AssignEmptyCoordination<T>(
        private val view: EmptyStateView,
        private val uiScheduler: Scheduler) : FlowableTransformer<T, T> {

    override fun apply(upstream: Flowable<T>): Publisher<T> {

        val delegate = HideAtStartShowAtError<T>(
                whenStart = view.hideEmptyState(),
                atError = view.showEmptyState(),
                errorPredicate = object : ErrorPredicate {
                    override fun evaluate(error: Throwable): Boolean = error is ContentNotFoundError
                },
                targetScheduler = uiScheduler
        )

        return upstream.compose(delegate)
    }

}
