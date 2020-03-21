package com.example.weatherapp.home.presentation.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherapp.R
import com.example.weatherapp.core.data.local.sharedpref.UserPreference
import dagger.android.AndroidInjection
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {
    @Inject
    lateinit var userPreference: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userPreference.getAccessToken()
    }
}
