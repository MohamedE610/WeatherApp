package com.example.weatherapp.weatherdetails.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.core.presentation.extentions.convertSecondsToDateTime
import com.example.weatherapp.core.presentation.extentions.loadIconWeather
import com.example.weatherapp.home.domain.model.DayTemperature
import kotlinx.android.synthetic.main.item_day_forecast.view.*

class DayForecastAdapter : RecyclerView.Adapter<DayForecastAdapter.DayForecastViewHolder>() {
    val backgroundList = intArrayOf(
        R.drawable.gradient_harmonic_energy,
        R.drawable.gradient_moss,
        R.drawable.gradient_majito,
        R.drawable.gradient_orange,
        R.drawable.gradient_sea_weed
    )
    val data = arrayListOf<DayTemperature>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayForecastViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_day_forecast, parent, false)
        return DayForecastViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: DayForecastViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    inner class DayForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: DayTemperature, position: Int) {
            itemView.background =
                ContextCompat.getDrawable(itemView.context, backgroundList[position % 5])

            itemView.tvDayDate.text = item.date.convertSecondsToDateTime()
            itemView.imgForecast.loadIconWeather(item.weatherList[0].icon)
            itemView.tvForeCastMain.text = item.weatherList[0].main
            itemView.tvForeCastDesc.text = item.weatherList[0].description
            itemView.tvTemperature.text = item.temp.day.toString()
        }
    }
}