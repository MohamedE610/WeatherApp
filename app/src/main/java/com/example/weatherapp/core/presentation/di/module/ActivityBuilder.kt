package com.example.weatherapp.core.presentation.di.module

import com.example.weatherapp.home.presentation.view.activity.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [])
    abstract fun bindHomeActivity(): HomeActivity
}