package com.smilebackapp.weather.core.application

import android.app.Activity
import android.app.Application
import android.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by idan on 06/11/2017.
 */
class App : Application(), HasActivityInjector {

    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    fun injectDependencies() {

    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}