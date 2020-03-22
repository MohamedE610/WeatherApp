package com.example.weatherapp.home.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.core.presentation.extentions.setVisible
import com.example.weatherapp.core.presentation.extentions.showToastGeneralError
import com.example.weatherapp.core.presentation.viewmodel.ViewModelFactory
import com.example.weatherapp.home.domain.model.CityWeather
import com.example.weatherapp.home.presentation.view.adapter.CityAdapter
import com.example.weatherapp.home.presentation.viewmodel.CityWeatherViewModel
import com.example.weatherapp.weatherdetails.presentation.view.activity.WeatherDetailsActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_home.*
import java.io.*
import javax.inject.Inject


class HomeFragment : Fragment() {

    private lateinit var cityAdapter: CityAdapter

    @Inject
    lateinit var cityWeatherFactory: ViewModelFactory<CityWeatherViewModel>
    private val cityWeatherViewModel by lazy {
        ViewModelProvider(activity!!, cityWeatherFactory).get(CityWeatherViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCityRecyclerView()
        initAutoCompleteTextView()
        observeOnCityWeatherList()
        cityWeatherViewModel.getAllCitiesWeatherFromDB()
    }

    private fun initAutoCompleteTextView() {
        val citiesNames: List<String> = getCitiesNames()
        val arrayAdapter =
            ArrayAdapter<String>(context!!, android.R.layout.select_dialog_item, citiesNames)
        acSearch?.setAdapter(arrayAdapter)
        acSearch?.threshold = 1
        observeOnSearchActionClicked()
        observeOnItemClicked()
    }

    private fun observeOnItemClicked() {
        acSearch?.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, _, _ ->
                val selectedCity = acSearch.text.toString()
                navigateToWeatherDetailsActivity(selectedCity)
            }
    }

    private fun observeOnSearchActionClicked() {
        acSearch?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                acSearch?.dismissDropDown()
                val selectedCity = acSearch.text.toString()
                navigateToWeatherDetailsActivity(selectedCity)
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    private fun navigateToWeatherDetailsActivity(selectedCity: String) {
        val intent = WeatherDetailsActivity.getIntent(context!!, selectedCity)
        startActivity(intent)
    }

    private fun getCitiesNames(): List<String> {
        val citiesNames = HashSet<String>()
        val type = object : TypeToken<Map<String, List<String>>>() {
        }.type
        val fileContent = getFileContent()
        val countryCitiesHashMap = Gson().fromJson<Map<String, List<String>>>(fileContent, type)
        for (country in countryCitiesHashMap) {
            citiesNames.addAll(country.value)
        }
        return citiesNames.toList()
    }

    private fun getFileContent(): String {
        val inputStream: InputStream = resources.openRawResource(R.raw.countries_to_cities)
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        inputStream.use { inStream ->
            val reader: Reader = BufferedReader(InputStreamReader(inStream, "UTF-8"))
            var n: Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)
            }
        }

        return writer.toString()
    }

    private fun initCityRecyclerView() {
        cityAdapter = CityAdapter()
        rvCities?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvCities.adapter = cityAdapter
        observeOnRemoveIconClicked()
    }

    private fun observeOnRemoveIconClicked() {
        cityAdapter.onRemoveIconClickedLiveEvent.observe(viewLifecycleOwner,
            Observer {
                it?.let { cityWeather ->
                    cityWeatherViewModel.deleteCityWeather(cityWeather.city.id)
                    cityAdapter.data.remove(cityWeather)
                    cityAdapter.notifyDataSetChanged()
                }
            })
    }

    private fun observeOnCityWeatherList() {
        cityWeatherViewModel.localCitiesWeatherLiveData.observe(this,
            successObserver = Observer { displayCities(it) },
            loadingObserver = Observer { showLoading(it) },
            commonErrorObserver = Observer { activity?.showToastGeneralError() })
    }

    private fun showLoading(it: Boolean?) {
        it?.let { loading ->
            if (loading) {
                loadingHomeSkeleton?.setVisible(true)
            } else {
                loadingHomeSkeleton?.setVisible(false)
            }
        }
    }

    private fun displayCities(it: List<CityWeather>) {
        if (it.isNullOrEmpty()) {
            activity?.showToastGeneralError()
            return
        }

        cityAdapter.data.clear()
        cityAdapter.data.addAll(it)

    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}
