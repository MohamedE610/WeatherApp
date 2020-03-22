package com.example.weatherapp.weatherdetails.presentation.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.weatherapp.R
import com.example.weatherapp.core.presentation.extentions.addFragment
import com.example.weatherapp.core.presentation.extentions.replaceFragment
import com.example.weatherapp.home.domain.model.CityWeather
import com.example.weatherapp.weatherdetails.presentation.view.fragment.WeatherDetailsFragment
import dagger.android.support.DaggerAppCompatActivity

class WeatherDetailsActivity : DaggerAppCompatActivity() {
    var onActivityBackPressedCallback: OnActivityBackPressedCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_details)
        val cityName = intent.getStringExtra(CITY_NAME) ?: ""
        if (cityName.isNotEmpty())
            title = cityName

        val citeWeather = intent.getParcelableExtra<CityWeather>(CITY)
        citeWeather?.let { title = it.city.name }
        addWeatherDetailsFragment()
    }

    private fun addWeatherDetailsFragment() {
        val fragment = WeatherDetailsFragment.newInstance(intent.extras)
        val containerID = R.id.weatherDetailsFragmentContainer
        replaceFragment(fragment, containerID)
    }

    override fun onBackPressed() {
        onActivityBackPressedCallback?.onActivityBackPressed()
        super.onBackPressed()
    }

    companion object {
        const val CITY_NAME = "city_name"
        const val CITY = "city"
        fun getIntent(context: Context, cityName: String): Intent {
            val intent = Intent(context, WeatherDetailsActivity::class.java)
            intent.putExtra(CITY_NAME, cityName)
            return intent
        }

        fun getIntent(context: Context, city: CityWeather): Intent {
            val intent = Intent(context, WeatherDetailsActivity::class.java)
            val args = Bundle()
            args.putParcelable(CITY, city)
            intent.putExtras(args)
            return intent
        }
    }

    interface OnActivityBackPressedCallback {
        fun onActivityBackPressed()
    }
}
