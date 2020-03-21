package com.example.weatherapp.home.domain.model

data class CityWeather(
    val city: City,
    val cod: String,
    val cnt: Int,
    val list: List<DayTemperature>
)

data class City(
    val id: String,
    val name: String
)

data class DayTemperature(
    val date: Long,
    val temp: Temperature,
    val weatherList: List<Weather>
)

data class Temperature(
    val day: Long,
    val min: Long,
    val max: Long
)

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)