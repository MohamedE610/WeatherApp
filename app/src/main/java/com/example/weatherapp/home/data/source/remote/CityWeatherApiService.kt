package com.example.weatherapp.home.data.source.remote

import com.example.weatherapp.home.data.source.remote.model.CityWeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CityWeatherApiService {

    @GET("/forecast/daily")
    fun getCityWeatherByLatLong(
        @Query("lat") lat: Long,
        @Query("lon") lon: Long,
        @Query("cnt") numOfDays: Int = 7
    ): Single<CityWeatherResponse>


    @GET("/forecast/daily")
    fun getCityWeatherByName(
        @Query("q") cityName: String,
        @Query("cnt") numOfDays: Int = 7
    ): Single<CityWeatherResponse>
}