package com.example.weatherapp.core.data.remote.api

import android.annotation.SuppressLint
import android.util.Log
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.core.data.local.sharedpref.UserPreference
import com.example.weatherapp.core.presentation.common.AppConstants
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceGenerator {


    @SuppressLint("LogNotTimber")
    fun <S> createService(
        serviceClass: Class<S>
    ): S {

        val baseURL = AppConstants.WEATHER_BASE_URL

        val httpClient = OkHttpClient.Builder()
        val builder = Retrofit.Builder()

        httpClient.connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.HEADERS
            httpClient.addInterceptor(logging)
        }

        val gson = GsonBuilder().enableComplexMapKeySerialization()
            .setLenient()
            .create()

        builder.baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

        val parametersInterceptor = Interceptor { chain ->
            val url = chain.request()
                .url().newBuilder()
                .addQueryParameter("appid", AppConstants.WEATHER_API_KEY)
                .addQueryParameter("units", "metric")//For temperature in Celsius
                .build()
            val request = chain.request().newBuilder().url(url).build()
            chain.proceed(request)
        }

        httpClient.addInterceptor(parametersInterceptor)

        builder.client(httpClient.build())
        val retrofit = builder.build()
        return retrofit.create(serviceClass)
    }
}