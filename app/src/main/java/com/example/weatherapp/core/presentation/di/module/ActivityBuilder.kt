package com.example.weatherapp.core.presentation.di.module

import com.example.weatherapp.home.presentation.di.HomeFragmentBuilder
import com.example.weatherapp.home.presentation.view.activity.HomeActivity
import com.example.weatherapp.weatherdetails.presentation.di.WeatherDetailsFragmentBuilder
import com.example.weatherapp.weatherdetails.presentation.view.activity.WeatherDetailsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [HomeFragmentBuilder::class])
    abstract fun bindHomeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [WeatherDetailsFragmentBuilder::class])
    abstract fun bindWeatherDetailsActivity(): WeatherDetailsActivity
}