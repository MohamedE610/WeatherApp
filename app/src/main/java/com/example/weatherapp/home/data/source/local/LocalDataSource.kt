package com.example.weatherapp.home.data.source.local

import com.example.weatherapp.home.data.source.local.entity.CityWeatherEntity
import io.reactivex.Completable
import io.reactivex.Single

interface LocalDataSource {
    fun getAllCitiesWeather(): Single<List<CityWeatherEntity>>
    fun saveCityWeather(cityWeatherEntity: CityWeatherEntity): Completable
    fun deleteCityWeather(cityId: String): Completable
    fun saveDefaultCityId(cityId:String)
    fun getDefaultCityId():String
    fun getCitiesWeatherCount():Single<Int>
}