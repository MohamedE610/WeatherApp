package com.example.weatherapp.weatherdetails.presentation.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherapp.R
import dagger.android.support.DaggerAppCompatActivity

class WeatherDetailsActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_details)
    }
}
