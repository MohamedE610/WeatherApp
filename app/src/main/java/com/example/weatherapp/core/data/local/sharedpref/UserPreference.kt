package com.example.weatherapp.core.data.local.sharedpref

import android.content.SharedPreferences
import com.example.weatherapp.core.presentation.di.qualifier.PreferenceInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreference @Inject constructor(
    @PreferenceInfo private val sharedPreferences: SharedPreferences
) {

    fun getDefaultCityId(): String {
        return sharedPreferences.getString(City_ID, "") ?: ""
    }

    fun saveDefaultCityId(cityId: String) {
        val editor = sharedPreferences.edit()
        editor.putString(City_ID, cityId)
        editor.apply()
    }

    companion object {
        const val City_ID = "city_id"
    }

}