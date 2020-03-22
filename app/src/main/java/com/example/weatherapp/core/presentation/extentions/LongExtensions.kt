package com.example.weatherapp.core.presentation.extentions

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("SimpleDateFormat")
fun Long.convertSecondsToDateTime(): String {
    val formatter = SimpleDateFormat("EEEE, MMMM d, yyyy HH:mm")
    return formatter.format(Date(this * 1000L))
}