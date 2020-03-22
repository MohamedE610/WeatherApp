package com.example.weatherapp.home.domain.interactor

import com.example.weatherapp.home.domain.model.CityWeather
import com.example.weatherapp.home.domain.repository.CityWeatherRepository
import javax.inject.Inject

class SaveCityWeatherUseCase @Inject constructor(private val repository: CityWeatherRepository) {
    fun execute(cityWeather: CityWeather) = repository.saveCityWeather(cityWeather)
}