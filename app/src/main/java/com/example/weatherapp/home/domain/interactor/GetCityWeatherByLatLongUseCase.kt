package com.example.weatherapp.home.domain.interactor

import com.example.weatherapp.home.domain.repository.CityWeatherRepository
import javax.inject.Inject

class GetCityWeatherByLatLongUseCase @Inject constructor(private val cityWeatherRepository: CityWeatherRepository) {
    fun execute(lat: Long, long: Long) = cityWeatherRepository.getCityWeatherByLatLong(lat, long)
}