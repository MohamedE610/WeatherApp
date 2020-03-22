package com.example.weatherapp.weatherdetails.presentation.view.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.weatherapp.R
import com.example.weatherapp.core.presentation.extentions.*
import com.example.weatherapp.core.presentation.viewmodel.ViewModelFactory
import com.example.weatherapp.home.domain.model.CityWeather
import com.example.weatherapp.home.presentation.viewmodel.CityWeatherViewModel
import com.example.weatherapp.weatherdetails.presentation.view.activity.WeatherDetailsActivity
import com.example.weatherapp.weatherdetails.presentation.view.adapter.DayForecastAdapter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_weather_details.*
import javax.inject.Inject


class WeatherDetailsFragment : Fragment(), WeatherDetailsActivity.OnActivityBackPressedCallback {
    private lateinit var daysForecastAdapter: DayForecastAdapter
    private var cityWeather: CityWeather? = null

    @Inject
    lateinit var cityWeatherFactory: ViewModelFactory<CityWeatherViewModel>
    private val cityWeatherViewModel by lazy {
        ViewModelProvider(activity!!, cityWeatherFactory).get(CityWeatherViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        setOnActivityBackPressedCallback()
        arguments?.let {}
    }

    private fun setOnActivityBackPressedCallback() {
        if (activity is WeatherDetailsActivity)
            (activity as WeatherDetailsActivity).onActivityBackPressedCallback = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
        loadArgs()
    }

    private fun initViews() {
        initDaysForecastRecyclerView()
        fabSaveCityWeather.setOnClickListener {
            cityWeather?.let {
                saveCityWeatherIntoDB()
            }
        }
    }

    private fun initDaysForecastRecyclerView() {
        rvDaysForecast.layoutManager =
            LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
        daysForecastAdapter = DayForecastAdapter()
        rvDaysForecast.adapter = daysForecastAdapter
    }

    private fun initObservers() {
        observeOnCityWeather()
        observeOnSaveCityWeatherSuccessfully()
        observeOnSavedCitiesCount()
    }

    private fun observeOnSavedCitiesCount() {
        cityWeatherViewModel.savedCitiesCountLiveEvent.observe(viewLifecycleOwner,
            Observer {
                // check if count of cities less than 5 to save otherwise ignore
                if (it < MAX_CITIES_NUM_IN_HOME)
                    cityWeather?.let { cw -> cityWeatherViewModel.saveCityWeather(cw) }
                else {
                    showToast("You can't add more than 5 cities to home screen")
                }
            })
    }

    private fun observeOnSaveCityWeatherSuccessfully() {
        cityWeatherViewModel.saveCityWeatherSuccessfully.observe(viewLifecycleOwner,
            Observer {
                it?.let { saveSuccessfully ->
                    if (saveSuccessfully)
                        showToast("Saved successfully !!")
                    else
                        activity?.showToastGeneralError()
                }
            })
    }

    private fun observeOnCityWeather() {
        cityWeatherViewModel.cityWeatherByNameLiveData.observe(this,
            successObserver = Observer { displayDaysForecast(it) },
            loadingObserver = Observer { showLoading(it) },
            commonErrorObserver = Observer {
                val isInternetAvailable = activity?.isInternetAvailable() ?: false
                if (isInternetAvailable)
                    activity?.showToastGeneralError()
                else
                    activity?.showToastNetworkError()
            })
    }

    private fun displayDaysForecast(it: CityWeather?) {
        it?.let { cityWeather ->
            fabSaveCityWeather.setVisible(true)
            this.cityWeather = cityWeather
            daysForecastAdapter.data.clear()
            daysForecastAdapter.data.addAll(cityWeather.list)
        }
    }

    private fun showLoading(it: Boolean?) {
        it?.let { loading ->
            loadingDetailsSkeleton.setVisible(loading)
        }
    }

    // check if count of cities less than 5 to save otherwise ignore
    private fun saveCityWeatherIntoDB() {
        cityWeatherViewModel.getSavedCitiesCount()
    }

    private fun loadArgs() {
        arguments?.let {
            val cityWeather = it.getParcelable<CityWeather>(WeatherDetailsActivity.CITY)
            if (cityWeather != null) {
                this.cityWeather = cityWeather
                fabSaveCityWeather.setVisible(true)
                displayDaysForecast(cityWeather)
                return
            }

            val cityName = it.getString(WeatherDetailsActivity.CITY_NAME, "")
            cityWeatherViewModel.getCityWeatherByName(cityName)
        }
    }

    override fun onActivityBackPressed() {
        sendResultBackToHomeActivity()
    }

    private fun sendResultBackToHomeActivity() {
        val resultIntent = Intent()
        resultIntent.putExtra(WeatherDetailsActivity.CITY, cityWeather)
        activity?.setResult(RESULT_OK, resultIntent)
    }

    companion object {
        private const val MAX_CITIES_NUM_IN_HOME = 5
        @JvmStatic
        fun newInstance(extras: Bundle?) = WeatherDetailsFragment().apply {
            extras?.let {
                arguments = extras
            }
        }
    }
}
