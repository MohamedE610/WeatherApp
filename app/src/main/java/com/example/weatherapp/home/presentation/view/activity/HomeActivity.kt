package com.example.weatherapp.home.presentation.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.weatherapp.R
import com.example.weatherapp.core.presentation.extentions.addFragment
import com.example.weatherapp.core.presentation.extentions.replaceFragment
import com.example.weatherapp.service.UpdateForecastService
import com.example.weatherapp.home.presentation.view.fragment.HomeFragment
import com.example.weatherapp.weatherdetails.presentation.view.fragment.WeatherDetailsFragment
import dagger.android.support.DaggerAppCompatActivity

class HomeActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addHomeFragment()
        startUpdateForecastService()
    }

    private fun addHomeFragment() {
        val fragment = HomeFragment()
        val containerId = R.id.homeFragmentContainer
        replaceFragment(fragment, containerId)
    }

    private fun startUpdateForecastService() {
        Handler().postDelayed(
            { startService(Intent(this, UpdateForecastService::class.java)) },
            1000
        )
    }

    private fun stopUpdateForecastService() {
        stopService(Intent(this, UpdateForecastService::class.java))
    }

    override fun onDestroy() {
        stopUpdateForecastService()
        super.onDestroy()
    }
}

