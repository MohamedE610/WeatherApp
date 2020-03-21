package com.example.weatherapp.home.presentation.view.activity

import android.os.Bundle
import com.example.weatherapp.R
import com.example.weatherapp.core.data.local.sharedpref.UserPreference
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class HomeActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
