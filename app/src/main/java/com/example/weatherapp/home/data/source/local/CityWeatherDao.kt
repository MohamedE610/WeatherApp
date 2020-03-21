package com.example.weatherapp.home.data.source.local

import androidx.room.*
import com.example.weatherapp.home.data.source.local.entity.CityWeatherEntity

@Dao
interface CityWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCityWeather(cityWeatherEntity: CityWeatherEntity)

    @Query("select * from CityWeather")
    fun getAllCitiesWeather(): List<CityWeatherEntity>

    @Query("delete from  CityWeather where id = :cityId")
    fun deleteCityWeather(cityId: String)
}