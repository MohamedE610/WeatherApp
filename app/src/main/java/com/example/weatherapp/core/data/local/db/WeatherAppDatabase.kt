package com.example.weatherapp.core.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [UserInfoEntity::class]
    , version = 1, exportSchema = false
)

@TypeConverters(DBConverters::class)
abstract class WeatherAppDatabase : RoomDatabase() {
    abstract fun userInfoDao(): UserInfoDao
}
