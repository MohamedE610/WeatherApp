package com.example.weatherapp.core.data.local.sharedpref

import android.content.SharedPreferences
import com.example.weatherapp.core.presentation.di.qualifier.PreferenceInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreference @Inject constructor(
    @PreferenceInfo private val sharedPreferences: SharedPreferences
) {

    fun getAccessToken(): String {
        return sharedPreferences.getString(ACCESS_TOKEN, "") ?: ""
    }

    fun saveAccessToken(accessToken: String) {
        val editor = sharedPreferences.edit()
        editor.putString(ACCESS_TOKEN, accessToken)
        editor.apply()
    }

    companion object {
        const val ACCESS_TOKEN = "PREFE_ACCESS_TOKEN"
    }

}