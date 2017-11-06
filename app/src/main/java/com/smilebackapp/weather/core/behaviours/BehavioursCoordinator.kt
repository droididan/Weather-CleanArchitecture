package com.smilebackapp.weather.core.behaviours

import com.smilebackapp.weather.core.behaviours.emptystate.AssignEmptyCoordination
import com.smilebackapp.weather.core.behaviours.errornetworkingstate.NetworkingErrorCoordination
import com.smilebackapp.weather.core.behaviours.errorstate.AssignErrorCoordination
import com.smilebackapp.weather.core.behaviours.loadingstate.LoadingCoordination
import com.smilebackapp.weather.core.behaviours.refreshtooglestate.RefreshToogleCoordination
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import org.reactivestreams.Publisher

class BehavioursCoordinator<T>(private val dealWithEmptyState: AssignEmptyCoordination<T>,
                               private val loadingCoordination: LoadingCoordination<T>,
                               private val errorCoordination: AssignErrorCoordination<T>,
                               private val networkingCoordination: NetworkingErrorCoordination<T>,
                               private val toogleCoordination: RefreshToogleCoordination<T>) :
        FlowableTransformer<T, T> {

    override fun apply(upstream: Flowable<T>): Publisher<T> {
        return upstream
                .compose(dealWithEmptyState)
                .compose(loadingCoordination)
                .compose(errorCoordination)
                .compose(networkingCoordination)
                .compose(toogleCoordination)
    }

}
