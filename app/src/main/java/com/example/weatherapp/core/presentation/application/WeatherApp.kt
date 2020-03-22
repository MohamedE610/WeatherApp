package com.example.weatherapp.core.presentation.application

import android.app.Activity
import android.app.Application
import android.app.Service
import android.content.BroadcastReceiver
import com.example.weatherapp.core.presentation.di.component.DaggerAppComponent
import dagger.android.*
import javax.inject.Inject

class WeatherApp : Application(), HasActivityInjector, HasServiceInjector,
    HasBroadcastReceiverInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var serviceDispatchingAndroidInjector: DispatchingAndroidInjector<Service>

    @Inject
    lateinit var broadcastReceiverDispatchingAndroidInjector: DispatchingAndroidInjector<BroadcastReceiver>

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    override fun serviceInjector(): AndroidInjector<Service> {
        return serviceDispatchingAndroidInjector
    }

    override fun broadcastReceiverInjector(): AndroidInjector<BroadcastReceiver> {
        return broadcastReceiverDispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().application(this).build().inject(this)
    }


}