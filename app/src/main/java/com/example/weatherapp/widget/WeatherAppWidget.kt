package com.example.weatherapp.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.widget.RemoteViews
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.AppWidgetTarget
import com.bumptech.glide.request.transition.Transition
import com.example.weatherapp.R
import com.example.weatherapp.core.presentation.common.AppConstants
import com.example.weatherapp.home.domain.model.CityWeather
import com.example.weatherapp.home.presentation.viewmodel.CityWeatherViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject


/**
 * Implementation of App Widget functionality.
 */
class WeatherAppWidget : AppWidgetProvider() {
    @Inject
    lateinit var cityWeatherViewModel: CityWeatherViewModel

    override fun onReceive(context: Context?, intent: Intent?) {
        AndroidInjection.inject(this, context)
        super.onReceive(context, intent)
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(
                context,
                appWidgetManager,
                appWidgetId
            )
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    private fun updateAppWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        cityWeatherViewModel.getAllCitiesWeatherFromDB()
        cityWeatherViewModel.localCitiesWeatherLiveData.observeForever(
            Observer {
                it?.let { list ->
                    if (list.isNotEmpty())
                        updateWidget(list, context, appWidgetManager, appWidgetId)
                }
                cityWeatherViewModel.localCitiesWeatherLiveData.removeObserver()
            }
        )
    }

    private fun updateWidget(
        list: List<CityWeather>,
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        val defaultCity = getDefaultCity(list)
        // Construct the RemoteViews object
        val views = RemoteViews(
            context.packageName,
            R.layout.weather_app_widget
        )

        views.setTextViewText(R.id.tvWidgetCityName, defaultCity.city.name)
        views.setTextViewText(R.id.tvWidgetTemperature, defaultCity.list[0].temp.day.toString())
        views.setTextViewText(R.id.tvWidgetWeatherStatus, defaultCity.list[0].weatherList[0].main)
        loadImageIntoRemoteViews(defaultCity, context, views, appWidgetId)
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }

    private fun getDefaultCity(list: List<CityWeather>): CityWeather {
        val defaultCityId = cityWeatherViewModel.getDefaultCityId()
        for (cityWeather in list)
            if (cityWeather.city.id == defaultCityId)
                return cityWeather

        return list[0]
    }

    private fun loadImageIntoRemoteViews(
        defaultCity: CityWeather,
        context: Context,
        views: RemoteViews,
        appWidgetId: Int
    ) {
        val url =
            "${AppConstants.WEATHER_MEDIA_URL}${defaultCity.list[0].weatherList[0].icon}${AppConstants.WEATHER_MEDIA_EXTENSION}"
        val awt: AppWidgetTarget =
            object : AppWidgetTarget(context, R.id.imgWidgetForecast, views, appWidgetId) {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?
                ) {
                    super.onResourceReady(resource, transition)
                }
            }

        val options: RequestOptions =
            RequestOptions().override(512, 512)
                .placeholder(R.drawable.ic_weather_widget)
                .error(R.drawable.ic_weather_widget)

        Glide.with(context.applicationContext)
            .asBitmap()
            .load(url)
            .apply(options)
            .into(awt)
    }

}
