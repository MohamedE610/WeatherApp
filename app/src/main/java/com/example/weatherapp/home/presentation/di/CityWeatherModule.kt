package com.example.weatherapp.home.presentation.di

import com.example.weatherapp.core.data.remote.api.ServiceGenerator
import com.example.weatherapp.home.data.source.remote.CityWeatherApiService
import dagger.Module
import dagger.Provides

@Module
class CityWeatherModule {

    @Provides
    fun providesCityWeatherApiService() =
        ServiceGenerator().createService(CityWeatherApiService::class.java)
}