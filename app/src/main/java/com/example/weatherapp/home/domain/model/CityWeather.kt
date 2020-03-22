package com.example.weatherapp.home.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CityWeather(
    val city: City,
    val cod: String,
    val cnt: Int,
    val list: List<DayTemperature>
) : Parcelable

@Parcelize
data class City(
    val id: String,
    val name: String
) : Parcelable

@Parcelize
data class DayTemperature(
    val date: Long,
    val temp: Temperature,
    val weatherList: List<Weather>
) : Parcelable

@Parcelize
data class Temperature(
    val day: Double,
    val min: Double,
    val max: Double
) : Parcelable

@Parcelize
data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
) : Parcelable