package com.example.weatherapp.home.presentation.viewmodel

import com.example.weatherapp.core.presentation.modelwrapper.ObservableResource
import com.example.weatherapp.core.presentation.viewmodel.BaseViewModel
import com.example.weatherapp.home.domain.interactor.GetAllCitiesWeatherFromDBUseCase
import com.example.weatherapp.home.domain.interactor.GetCityWeatherByCityNameUseCase
import com.example.weatherapp.home.domain.interactor.GetCityWeatherByLatLongUseCase
import com.example.weatherapp.home.domain.model.CityWeather
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

import javax.inject.Inject

class CityWeatherViewModel @Inject constructor(
    private val getAllCitiesWeatherFromDBUseCase: GetAllCitiesWeatherFromDBUseCase,
    private val getCityWeatherByCityNameUseCase: GetCityWeatherByCityNameUseCase,
    private val getCityWeatherByLatLongUseCase: GetCityWeatherByLatLongUseCase
) : BaseViewModel() {

    val localCitiesWeatherLiveData by lazy {
        ObservableResource<List<CityWeather>>()
    }

    val cityWeatherByLatLonLiveData by lazy {
        ObservableResource<CityWeather>()
    }

    val cityWeatherByNameLiveData by lazy {
        ObservableResource<CityWeather>()
    }

    fun getAllCitiesWeatherFromDB() {
        val disposable = getAllCitiesWeatherFromDBUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { localCitiesWeatherLiveData.loading.postValue(true) }
            .doFinally { localCitiesWeatherLiveData.loading.postValue(false) }
            .subscribe({
                localCitiesWeatherLiveData.value = it
            }, {
                localCitiesWeatherLiveData.error.value = it
            })

        addDisposable(disposable)
    }

    fun getCityWeatherByName(cityName: String) {
        val disposable = getCityWeatherByCityNameUseCase.execute(cityName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { cityWeatherByNameLiveData.loading.postValue(true) }
            .doFinally { cityWeatherByNameLiveData.loading.postValue(false) }
            .subscribe({
                cityWeatherByNameLiveData.value = it
            }, {
                cityWeatherByNameLiveData.error.value = it
            })
        addDisposable(disposable)
    }

    fun getCityWeatherByLatLon(lat: Long, lon: Long) {
        val disposable = getCityWeatherByLatLongUseCase.execute(lat, lon)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { cityWeatherByLatLonLiveData.loading.postValue(true) }
            .doFinally { cityWeatherByLatLonLiveData.loading.postValue(false) }
            .subscribe({
                cityWeatherByLatLonLiveData.value = it
            }, {
                cityWeatherByLatLonLiveData.error.value = it
            })
    }
}