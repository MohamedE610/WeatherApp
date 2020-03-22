package com.example.weatherapp.home.data.source.local

import com.example.weatherapp.core.data.local.sharedpref.UserPreference
import com.example.weatherapp.home.data.source.local.entity.CityWeatherEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val cityWeatherDao: CityWeatherDao,
    private val userPreference: UserPreference
) :
    LocalDataSource {
    override fun getAllCitiesWeather(): Single<List<CityWeatherEntity>> =
        Single.fromCallable { cityWeatherDao.getAllCitiesWeather() }

    override fun saveCityWeather(cityWeatherEntity: CityWeatherEntity): Completable =
        Completable.fromCallable { cityWeatherDao.insertCityWeather(cityWeatherEntity) }

    override fun deleteCityWeather(cityId: String): Completable =
        Completable.fromCallable { cityWeatherDao.deleteCityWeather(cityId) }

    override fun saveDefaultCityId(cityId: String) {
        userPreference.saveDefaultCityId(cityId)
    }

    override fun getDefaultCityId(): String = userPreference.getDefaultCityId()
    override fun getCitiesWeatherCount(): Single<Int> =
        Single.fromCallable { cityWeatherDao.getCitiesWeatherCount() }
}