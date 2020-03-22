package com.example.weatherapp.home.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.core.presentation.modelwrapper.SingleLiveEvent
import com.example.weatherapp.home.domain.model.CityWeather
import kotlinx.android.synthetic.main.item_city_weather.view.*

class CityAdapter : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {
    val onRemoveIconClickedLiveEvent = SingleLiveEvent<CityWeather>()

    val backgroundList = intArrayOf(
        R.drawable.gradient_harmonic_energy,
        R.drawable.gradient_moss,
        R.drawable.gradient_majito,
        R.drawable.gradient_orange,
        R.drawable.gradient_sea_weed
    )
    val data = arrayListOf<CityWeather>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_city_weather, parent, false)
        return CityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    inner class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: CityWeather, position: Int) {
            itemView.tvCityName.text = item.city.name
            itemView.tvTemperature.text = item.list[0].temp.day.toString()
            itemView.clCityBackground.background =
                ContextCompat.getDrawable(itemView.context, backgroundList[position % 5])
            itemView.imgRemoveCity.setOnClickListener {
                onRemoveIconClickedLiveEvent.value = item
            }
        }
    }
}