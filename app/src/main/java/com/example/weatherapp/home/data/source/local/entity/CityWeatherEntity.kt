package com.example.weatherapp.home.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.weatherapp.core.data.local.db.DBConverters
import com.example.weatherapp.home.data.source.remote.model.CityModel
import com.example.weatherapp.home.data.source.remote.model.DayTemperatureModel

@Entity(tableName = "CityWeather")
data class CityWeatherEntity(
    //cityID
    @PrimaryKey()
    @ColumnInfo(name = "id")
    var id: String,

    @TypeConverters(DBConverters::class)
    @ColumnInfo(name = "city")
    var city: CityModel,

    @ColumnInfo(name = "cod")
    var cod: String,

    @ColumnInfo(name = "cnt")
    var cnt: Int,

    @TypeConverters(DBConverters::class)
    @ColumnInfo(name = "list")
    var list: List<DayTemperatureModel>
)