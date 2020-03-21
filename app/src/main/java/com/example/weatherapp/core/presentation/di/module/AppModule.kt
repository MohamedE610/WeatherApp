package com.example.weatherapp.core.presentation.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.weatherapp.core.presentation.common.AppConstants
import com.example.weatherapp.core.data.local.db.WeatherAppDatabase
import com.example.weatherapp.core.presentation.di.qualifier.DatabaseInfo
import com.example.weatherapp.core.presentation.di.qualifier.PreferenceInfo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @DatabaseInfo
    fun provideDatabaseName(): String {
        return AppConstants.DB_NAME
    }

    @Provides
    @PreferenceInfo
    fun providePreferenceName(): String {
        return AppConstants.PREF_NAME
    }

    @Provides
    @Singleton
    @PreferenceInfo
    fun providePreferenceObj(@PreferenceInfo spName: String, application: Application): SharedPreferences {
        return application.getSharedPreferences(spName, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideWeatherAppDatabase(@DatabaseInfo dbName: String, context: Context): WeatherAppDatabase {
        return Room.databaseBuilder(context, WeatherAppDatabase::class.java, dbName)
            .fallbackToDestructiveMigration()
            .build()
    }
}