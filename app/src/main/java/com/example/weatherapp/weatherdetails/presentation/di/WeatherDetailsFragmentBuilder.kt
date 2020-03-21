package com.example.weatherapp.weatherdetails.presentation.di

import com.example.weatherapp.weatherdetails.presentation.view.fragment.WeatherDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class WeatherDetailsFragmentBuilder{

    @ContributesAndroidInjector(modules = [])
    abstract fun bindWeatherDetailsFragment(): WeatherDetailsFragment
}