package com.example.weatherapp.home.domain.interactor

import com.example.weatherapp.home.domain.repository.CityWeatherRepository
import javax.inject.Inject

class GetSavedCitiesCountUseCase @Inject constructor(private val repository: CityWeatherRepository) {
    fun execute() = repository.getCitiesWeatherCount()
}