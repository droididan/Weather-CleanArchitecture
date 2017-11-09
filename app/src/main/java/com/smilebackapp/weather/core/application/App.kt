package com.smilebackapp.weather.core.application

import android.app.Activity
import android.app.Application
import android.app.Fragment
import com.github.ajalt.timberkt.Timber
import com.smilebackapp.weather.BuildConfig
import com.smilebackapp.weather.core.application.di.DaggerApplicationComponent
import com.smilebackapp.weather.core.database.RealmDatabase
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by idan on 06/11/2017.
 * Android App class
 */
class App : Application(), HasActivityInjector {

    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
        RealmDatabase.configure(this)
        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(timber.log.Timber.DebugTree())
        }
    }

    fun injectDependencies() {
        DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}