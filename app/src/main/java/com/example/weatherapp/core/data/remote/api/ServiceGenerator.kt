package com.example.weatherapp.core.data.remote.api

import android.annotation.SuppressLint
import android.util.Log
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.core.data.local.sharedpref.UserPreference
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
        serviceClass: Class<S>,
        userPreference: UserPreference
    ): S {

        val baseURL = ""
        val authToken = userPreference.getAccessToken()

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

        val interceptor: Interceptor

        authToken.let { Log.i("token", it) }

        var response: Response?
        interceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", authToken.trim())
                .addHeader("Content-Type", "application/json")
                .build()
            response = chain.proceed(request)
            response!!
        }

        if (!httpClient.interceptors().contains(interceptor)) {
            httpClient.addInterceptor(interceptor)
        }


        builder.client(httpClient.build())
        val retrofit = builder.build()
        return retrofit.create(serviceClass)
    }
}