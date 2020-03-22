package com.example.weatherapp.home.presentation.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.example.weatherapp.R
import com.example.weatherapp.core.presentation.viewmodel.ViewModelFactory
import com.example.weatherapp.home.presentation.viewmodel.CityWeatherViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var cityWeatherFactory: ViewModelFactory<CityWeatherViewModel>
    private val cityWeatherViewModel by lazy {
        ViewModelProvider(activity!!, cityWeatherFactory).get(CityWeatherViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        arguments?.let {}
        cityWeatherViewModel.getCityWeatherByName("london")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}
