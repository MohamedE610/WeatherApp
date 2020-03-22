package com.example.weatherapp.home.data.source.remote

import com.example.weatherapp.home.data.source.remote.model.CityWeatherResponse
import io.reactivex.Single
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val apiService: CityWeatherApiService) :
    RemoteDataSource {
    override fun getCityWeatherByLatLon(lat: Double, lon: Double): Single<CityWeatherResponse> =
        apiService.getCityWeatherByLatLong(lat, lon)

    override fun getCityWeatherByCityName(cityName: String): Single<CityWeatherResponse> =
        apiService.getCityWeatherByName(cityName)

}