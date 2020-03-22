package com.example.weatherapp.widget.di

import com.example.weatherapp.home.presentation.di.CityWeatherModule
import com.example.weatherapp.widget.WeatherAppWidget
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class WeatherAppWidgetBuilder {
    @ContributesAndroidInjector(modules = [CityWeatherModule::class])
    abstract fun bindWeatherAppWidget(): WeatherAppWidget
}