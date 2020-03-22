package com.example.weatherapp.home.domain.interactor

import com.example.weatherapp.home.domain.repository.CityWeatherRepository
import javax.inject.Inject

class DefaultCityIdUseCase @Inject constructor(private val repository: CityWeatherRepository) {
    fun getDefaultCityId() = repository.getDefaultCityId()
    fun saveDefaultCityId(cityId: String) = repository.saveDefaultCityId(cityId)
}