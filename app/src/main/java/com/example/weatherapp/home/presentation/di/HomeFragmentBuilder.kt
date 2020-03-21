package com.example.weatherapp.home.presentation.di

import com.example.weatherapp.home.presentation.view.fragment.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentBuilder {
    @ContributesAndroidInjector(modules = [])
    abstract fun bindHomeFragment(): HomeFragment
}