package com.amachikhin.testapplication.features.currentweather.di

import com.amachikhin.testapplication.di.AppComponent
import com.amachikhin.testapplication.features.base.presentation.ScreenScope
import com.amachikhin.testapplication.features.currentweather.presentation.CurrentWeatherFragment
import dagger.Component

@ScreenScope
@Component(
    dependencies = [AppComponent::class],
    modules = [CurrentWeatherModule::class, CurrentWeatherViewModelModule::class]
)
interface CurrentWeatherComponent {
    fun inject(fragment: CurrentWeatherFragment)

    @Component.Builder
    interface Builder {
        fun appComponent(appComponent: AppComponent): Builder

        fun build(): CurrentWeatherComponent
    }

    class Initializer private constructor() {
        companion object {
            fun init(
                appComponent: AppComponent
            ): CurrentWeatherComponent {
                return DaggerCurrentWeatherComponent
                    .builder()
                    .appComponent(appComponent)
                    .build()
            }
        }
    }
}
