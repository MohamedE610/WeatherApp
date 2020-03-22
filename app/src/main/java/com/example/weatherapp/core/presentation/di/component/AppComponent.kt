package com.example.weatherapp.core.presentation.di.component

import android.app.Application
import com.example.weatherapp.core.presentation.application.WeatherApp
import com.example.weatherapp.core.presentation.di.module.ActivityBuilder
import com.example.weatherapp.core.presentation.di.module.AppModule
import com.example.weatherapp.service.di.UpdateForecastServiceBuilder
import com.example.weatherapp.widget.di.WeatherAppWidgetBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilder::class,
        UpdateForecastServiceBuilder::class,
        WeatherAppWidgetBuilder::class,
        AppModule::class
    ]
)
interface AppComponent {
    fun inject(app: WeatherApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}
