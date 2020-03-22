package com.example.weatherapp.home.data.repository

import com.example.weatherapp.home.data.mapper.map
import com.example.weatherapp.home.data.mapper.mapIntoEntity
import com.example.weatherapp.home.data.source.local.LocalDataSource
import com.example.weatherapp.home.data.source.remote.RemoteDataSource
import com.example.weatherapp.home.domain.model.CityWeather
import com.example.weatherapp.home.domain.repository.CityWeatherRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class CityWeatherRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : CityWeatherRepository {
    override fun getCityWeatherByLatLong(lat: Long, long: Long): Single<CityWeather> =
        remoteDataSource.getCityWeatherByLatLon(lat, long).map { it.map() }

    override fun getCityWeatherByCityName(cityName: String): Single<CityWeather> =
        remoteDataSource.getCityWeatherByCityName(cityName).map { it.map() }

    override fun getAllCitiesWeatherFromDB(): Single<List<CityWeather>> =
        localDataSource.getAllCitiesWeather().map { list -> list.map { it.map() } }

    override fun deleteCityWeather(cityId: String): Completable =
        Completable.fromCallable { localDataSource.deleteCityWeather(cityId) }

    override fun saveCityWeather(cityWeather: CityWeather): Completable =
        Completable.fromCallable { localDataSource.saveCityWeather(cityWeather.mapIntoEntity()) }

}