package com.example.weatherapp.home.domain.interactor

import com.example.weatherapp.home.domain.repository.CityWeatherRepository
import javax.inject.Inject

class GetAllCitiesWeatherFromDBUseCase @Inject constructor(private val cityWeatherRepository: CityWeatherRepository) {
    fun execute() = cityWeatherRepository.getAllCitiesWeatherFromDB()
}