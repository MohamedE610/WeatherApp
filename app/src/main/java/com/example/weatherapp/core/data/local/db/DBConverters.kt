package com.example.weatherapp.core.data.local.db

import androidx.room.TypeConverter
import com.example.weatherapp.home.data.source.local.entity.CityWeatherEntity
import com.example.weatherapp.home.data.source.remote.model.CityModel
import com.example.weatherapp.home.data.source.remote.model.DayTemperatureModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DBConverters {

    @TypeConverter
    fun getCity(json: String?): CityModel? {
        if (json == null) {
            return null
        }
        val type = object : TypeToken<CityModel>() {
        }.type
        return Gson().fromJson<CityModel>(json, type)
    }

    @TypeConverter
    fun convertCityToString(city: CityModel?): String? {
        if (city == null) {
            return null
        }
        val type = object : TypeToken<CityModel>() {
        }.type
        return Gson().toJson(city, type)
    }

    @TypeConverter
    fun getDayTemperatureList(json: String?): List<DayTemperatureModel>? {
        if (json == null) {
            return null
        }
        val type = object : TypeToken<List<DayTemperatureModel>>() {
        }.type
        return Gson().fromJson<List<DayTemperatureModel>>(json, type)
    }

    @TypeConverter
    fun convertCityToString(list: List<DayTemperatureModel>?): String? {
        if (list == null) {
            return null
        }
        val type = object : TypeToken<DayTemperatureModel>() {
        }.type
        return Gson().toJson(list, type)
    }

}