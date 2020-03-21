package com.example.weatherapp.home.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R

class CityAdapter : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {
    private val data = arrayListOf<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_city_weather, parent, false)
        return CityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {

    }

    inner class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }
}