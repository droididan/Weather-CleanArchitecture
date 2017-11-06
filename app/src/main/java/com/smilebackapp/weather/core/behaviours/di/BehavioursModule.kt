package com.smilebackapp.weather.core.behaviours.di

import com.smilebackapp.weather.core.application.di.UIScheduler
import com.smilebackapp.weather.core.behaviours.BehavioursCoordinator
import com.smilebackapp.weather.core.behaviours.emptystate.AssignEmptyCoordination
import com.smilebackapp.weather.core.behaviours.errornetworkingstate.NetworkingErrorCoordination
import com.smilebackapp.weather.core.behaviours.errorstate.AssignErrorCoordination
import com.smilebackapp.weather.core.behaviours.loadingstate.LoadingCoordination
import com.smilebackapp.weather.core.behaviours.refreshtooglestate.RefreshToogleCoordination
import com.smilebackapp.weather.core.presentation.*
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler

@Module
class BehavioursModule {

    @Provides
    fun providesBehavioursCoordinator(assignEmptyState: AssignEmptyCoordination<Any>,
                                      loadingCoordination: LoadingCoordination<Any>,
                                      errorCoordination: AssignErrorCoordination<Any>,
                                      refreshToogleCoordination: RefreshToogleCoordination<Any>,
                                      networkingErrorCoordination: NetworkingErrorCoordination<Any>)
            : BehavioursCoordinator<Any> =
            BehavioursCoordinator(assignEmptyState, loadingCoordination, errorCoordination,
                    networkingErrorCoordination, refreshToogleCoordination)

    @Provides
    fun providesEmptyCoordination(view: EmptyStateView, @UIScheduler scheduler: Scheduler):
            AssignEmptyCoordination<Any> = AssignEmptyCoordination(view, scheduler)

    @Provides
    fun providesLoadingCoordination(view: LoadingView, @UIScheduler scheduler: Scheduler):
            LoadingCoordination<Any> = LoadingCoordination(view, scheduler)

    @Provides
    fun providesAssignErrorCoordination(view: ErrorStateView, @UIScheduler scheduler: Scheduler):
            AssignErrorCoordination<Any> = AssignErrorCoordination(view, scheduler)

    @Provides
    fun providesRefreshToogleCoordination(view: ToogleRefreshView, @UIScheduler scheduler: Scheduler):
            RefreshToogleCoordination<Any> = RefreshToogleCoordination(view, scheduler)

    @Provides
    fun providesNetworkingErrorCoordination(view: NetworkingView, @UIScheduler scheduler: Scheduler):
            NetworkingErrorCoordination<Any> = NetworkingErrorCoordination(view, scheduler)

}