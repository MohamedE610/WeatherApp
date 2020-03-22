package com.example.weatherapp.home.data.source.remote

import com.example.weatherapp.home.data.source.remote.model.CityWeatherResponse
import io.reactivex.Single

interface RemoteDataSource {
    fun getCityWeatherByLatLon(lat: Double, lon: Double): Single<CityWeatherResponse>
    fun getCityWeatherByCityName(cityName: String): Single<CityWeatherResponse>
}