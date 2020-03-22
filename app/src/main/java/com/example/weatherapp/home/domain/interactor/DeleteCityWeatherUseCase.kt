package com.example.weatherapp.home.domain.interactor

import com.example.weatherapp.home.domain.repository.CityWeatherRepository
import javax.inject.Inject

class DeleteCityWeatherUseCase @Inject constructor(private val repository: CityWeatherRepository) {
    fun execute(cityId: String) = repository.deleteCityWeather(cityId)
}