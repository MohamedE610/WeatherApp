package com.example.weatherapp.core.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weatherapp.home.data.source.local.CityWeatherDao
import com.example.weatherapp.home.data.source.local.entity.CityWeatherEntity

@Database(
    entities = [CityWeatherEntity::class]
    , version = 1, exportSchema = false
)

@TypeConverters(DBConverters::class)
abstract class WeatherAppDatabase : RoomDatabase() {
    abstract fun cityWeatherDao(): CityWeatherDao
}
