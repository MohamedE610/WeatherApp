package com.example.weatherapp.weatherdetails.presentation.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherapp.R
import dagger.android.support.DaggerAppCompatActivity

class WeatherDetailsActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_details)
        val cityName = intent.getStringExtra(CITY_NAME) ?: ""
        if (cityName.isNotEmpty())
            title = cityName
    }

    companion object {
        const val CITY_NAME = "city_name"
        fun getIntent(context: Context, cityName: String): Intent {
            val intent = Intent(context, WeatherDetailsActivity::class.java)
            intent.putExtra(CITY_NAME, cityName)
            return intent
        }
    }
}
