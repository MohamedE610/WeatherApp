package com.example.weatherapp.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.Observer
import com.example.weatherapp.home.presentation.viewmodel.CityWeatherViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class UpdateForecastService : Service() {

    @Inject
    lateinit var cityWeatherViewModel: CityWeatherViewModel

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
        Log.d("UpdateForecastService", "created")
        observeOnLocalCitiesWeather()
    }

    private fun observeOnLocalCitiesWeather() {
        cityWeatherViewModel.localCitiesWeatherLiveData.observeForever(
            Observer {
                if (it.isNullOrEmpty())
                    return@Observer
                for (city in it)
                    cityWeatherViewModel.saveCityWeather(city)

                Log.d("UpdateForecastService", "data Updated")
            })
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        updateLocalCitiesForecast()
        return START_STICKY
    }

    private fun updateLocalCitiesForecast() {
        cityWeatherViewModel.getAllCitiesWeatherFromDB()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("UpdateForecastService", "destroyed")
    }
}
