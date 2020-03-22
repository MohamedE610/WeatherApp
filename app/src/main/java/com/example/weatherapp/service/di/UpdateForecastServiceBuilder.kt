package com.example.weatherapp.service.di

import com.example.weatherapp.home.presentation.di.CityWeatherModule
import com.example.weatherapp.service.UpdateForecastService
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UpdateForecastServiceBuilder {
    @ContributesAndroidInjector(modules = [CityWeatherModule::class])
    abstract fun bindUpdateForecastService(): UpdateForecastService
}