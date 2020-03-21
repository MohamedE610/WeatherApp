package com.example.weatherapp.home.domain.interactor

import com.example.weatherapp.home.domain.repository.CityWeatherRepository
import javax.inject.Inject

class GetCityWeatherByCityNameUseCase @Inject constructor(private val cityWeatherRepository: CityWeatherRepository) {
    fun execute(cityName: String) = cityWeatherRepository.getCityWeatherByCityName(cityName)
}