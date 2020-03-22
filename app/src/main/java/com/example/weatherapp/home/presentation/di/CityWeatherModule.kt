package com.example.weatherapp.home.presentation.di

import com.example.weatherapp.core.data.local.db.WeatherAppDatabase
import com.example.weatherapp.core.data.remote.api.ServiceGenerator
import com.example.weatherapp.home.data.repository.CityWeatherRepositoryImpl
import com.example.weatherapp.home.data.source.local.LocalDataSource
import com.example.weatherapp.home.data.source.local.LocalDataSourceImpl
import com.example.weatherapp.home.data.source.remote.CityWeatherApiService
import com.example.weatherapp.home.data.source.remote.RemoteDataSource
import com.example.weatherapp.home.data.source.remote.RemoteDataSourceImpl
import com.example.weatherapp.home.domain.repository.CityWeatherRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module( includes = [CityWeatherModule.Declarations::class] )
class CityWeatherModule {
    @Provides
    fun providesCityWeatherApiService() =
        ServiceGenerator().createService(CityWeatherApiService::class.java)

    @Provides
    fun providesCityWeatherDao(db: WeatherAppDatabase) = db.cityWeatherDao()

    @Module
    interface Declarations {
        @Binds
        fun bindsLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

        @Binds
        fun bindsRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

        @Binds
        fun bindsCityWeatherRepository(repository: CityWeatherRepositoryImpl): CityWeatherRepository
    }
}