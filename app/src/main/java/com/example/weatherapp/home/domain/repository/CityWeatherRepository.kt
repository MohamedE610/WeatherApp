package com.example.weatherapp.home.domain.repository

import com.example.weatherapp.home.domain.model.CityWeather
import io.reactivex.Completable
import io.reactivex.Single

interface CityWeatherRepository {
    fun getCityWeatherByLatLong(lat: Long, long: Long): Single<CityWeather>
    fun getCityWeatherByCityName(cityName: String): Single<CityWeather>
    fun getAllCitiesWeatherFromDB(): Single<List<CityWeather>>
    fun deleteCityWeather(cityId: String): Completable
    fun saveCityWeather(cityWeather: CityWeather): Completable
}