package com.example.weatherapp.core.data.local.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DBConverters {

    @TypeConverter
    fun getGroupIdList(json: String?): List<String>? {
        if (json == null) {
            return null
        }
        val type = object : TypeToken<List<String>>() {
        }.type
        return Gson().fromJson<List<String>>(json, type)
    }

    @TypeConverter
    fun convertGroupIdListToString(list: List<String>?): String? {
        if (list == null) {
            return null
        }
        val type = object : TypeToken<List<String>>() {
        }.type
        return Gson().toJson(list, type)
    }

}