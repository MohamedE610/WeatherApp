package com.example.weatherapp.home.data.mapper

import com.example.weatherapp.home.data.source.local.entity.CityWeatherEntity
import com.example.weatherapp.home.data.source.remote.model.*
import com.example.weatherapp.home.domain.model.*

fun CityWeatherResponse.map() = CityWeather(city.map(), cod, cnt, list.map { it.map() })

fun CityWeatherEntity.map() = CityWeather(city.map(), cod, cnt, list.map { it.map() })

fun CityModel.map() = City(id, name)
fun DayTemperatureModel.map() = DayTemperature(date, temp.map(), weatherList.map { it.map() })
fun TemperatureModel.map() = Temperature(day, min, max)
fun WeatherModel.map() = Weather(id, main, description, icon)


fun CityWeather.mapIntoEntity() =
    CityWeatherEntity(city.id, city.map(), cod, cnt, list.map { it.map() })

fun City.map() = CityModel(id, name, "")
fun DayTemperature.map() = DayTemperatureModel(date, temp.map(), weatherList.map { it.map() })
fun Temperature.map() = TemperatureModel(day, min, max)
fun Weather.map() = WeatherModel(id, main, description, icon)