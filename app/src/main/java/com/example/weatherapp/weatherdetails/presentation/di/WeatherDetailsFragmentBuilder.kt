package com.example.weatherapp.weatherdetails.presentation.di

import com.example.weatherapp.home.presentation.di.CityWeatherModule
import com.example.weatherapp.weatherdetails.presentation.view.fragment.WeatherDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class WeatherDetailsFragmentBuilder{

    @ContributesAndroidInjector(modules = [CityWeatherModule::class])
    abstract fun bindWeatherDetailsFragment(): WeatherDetailsFragment
}