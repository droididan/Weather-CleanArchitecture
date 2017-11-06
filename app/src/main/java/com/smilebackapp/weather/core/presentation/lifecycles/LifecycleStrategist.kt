package com.smilebackapp.weather.core.presentation.lifecycles

import android.arch.lifecycle.LifecycleOwner
import com.smilebackapp.weather.core.presentation.lifecycles.DisposeStrategy
import io.reactivex.disposables.Disposable

open class LifecycleStrategist(owner: LifecycleOwner, var strategy: DisposeStrategy) {
    init {
        owner.lifecycle.addObserver(strategy)
    }

    fun applyStrategy(toDispose: Disposable) {
        strategy.addDisposable(toDispose)
    }
}
