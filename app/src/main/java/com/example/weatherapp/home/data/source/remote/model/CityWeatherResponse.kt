package com.example.weatherapp.home.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class CityWeatherResponse(
    @SerializedName("city") val city: CityModel,
    @SerializedName("cod") val cod: String,
    @SerializedName("cnt") val cnt: Int,
    @SerializedName("list") val list: List<DayTemperatureModel>
)

data class CityModel(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("country") val country: String
)

data class DayTemperatureModel(
    @SerializedName("dt") val date: Long,
    @SerializedName("temp") val temp: TemperatureModel,
    @SerializedName("weather") val weatherList: List<WeatherModel>
)

data class TemperatureModel(
    @SerializedName("day") val day: Double,
    @SerializedName("min") val min: Double,
    @SerializedName("max") val max: Double
)

data class WeatherModel(
    @SerializedName("id") val id: Int,
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)