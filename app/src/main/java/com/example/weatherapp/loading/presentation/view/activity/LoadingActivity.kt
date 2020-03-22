package com.example.weatherapp.loading.presentation.view.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.R
import com.example.weatherapp.core.presentation.extentions.isInternetAvailable
import com.example.weatherapp.core.presentation.extentions.showToastGeneralError
import com.example.weatherapp.core.presentation.extentions.showToastNetworkError
import com.example.weatherapp.core.presentation.viewmodel.ViewModelFactory
import com.example.weatherapp.home.presentation.view.activity.HomeActivity
import com.example.weatherapp.home.presentation.viewmodel.CityWeatherViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.android.AndroidInjection
import javax.inject.Inject

class LoadingActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    @Inject
    lateinit var cityWeatherFactory: ViewModelFactory<CityWeatherViewModel>
    private val cityWeatherViewModel by lazy {
        ViewModelProvider(this, cityWeatherFactory).get(CityWeatherViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        observeOnCityWeather()
        checkLocationPermission()
    }

    private fun observeOnCityWeather() {
        cityWeatherViewModel.cityWeatherByLatLonLiveData.observe(this,
            successObserver = Observer {
                cityWeatherViewModel.saveDefaultCityId(it.city.id)
                cityWeatherViewModel.saveCityWeather(it)
                navigateToHomeActivity()
            },
            loadingObserver = Observer { },
            commonErrorObserver = Observer {
                if (!isInternetAvailable())
                    showToastNetworkError()
                navigateToHomeActivity()
            })

        cityWeatherViewModel.cityWeatherByNameLiveData.observe(this,
            successObserver = Observer {
                cityWeatherViewModel.saveDefaultCityId(it.city.id)
                cityWeatherViewModel.saveCityWeather(it)
                navigateToHomeActivity()
            },
            loadingObserver = Observer { },
            commonErrorObserver = Observer {
                if (!isInternetAvailable())
                    showToastNetworkError()
                navigateToHomeActivity()
            })
    }

    private fun navigateToHomeActivity() {
        Handler().postDelayed({
            finish()
            startActivity(Intent(this, HomeActivity::class.java))
        }, 1000)
    }

    private fun getCurrentLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.let {
                    cityWeatherViewModel.getCityWeatherByLatLon(
                        location.latitude,
                        location.longitude
                    )
                    Log.d("CurrentLocation", "lat:${location.latitude}, long:${location.longitude}")
                } ?: cityWeatherViewModel.getCityWeatherByName(LONDON)
            }
    }

    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_Permission
            )
        } else {
            getCurrentLocation()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_LOCATION_Permission -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    getCurrentLocation()
                } else {
                    //in case no permission granted
                    cityWeatherViewModel.getCityWeatherByName(LONDON)
                }
                return
            }
            else -> Unit
        }
    }

    companion object {
        private const val REQUEST_LOCATION_Permission = 1001
        private const val LONDON = "london"
    }
}
