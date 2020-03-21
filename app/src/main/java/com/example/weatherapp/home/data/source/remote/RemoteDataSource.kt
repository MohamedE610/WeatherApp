package com.example.weatherapp.home.data.source.remote

import com.example.weatherapp.home.data.source.remote.model.CityWeatherResponse
import io.reactivex.Single

interface RemoteDataSource {
    fun getCityWeatherByLatLon(lat: Long, lon: Long): Single<CityWeatherResponse>
    fun getCityWeatherByCityName(cityName: String): Single<CityWeatherResponse>
}