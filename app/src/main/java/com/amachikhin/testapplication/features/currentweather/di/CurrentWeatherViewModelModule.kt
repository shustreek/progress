package com.amachikhin.testapplication.features.currentweather.di

import androidx.lifecycle.ViewModel
import com.amachikhin.testapplication.di.ViewModelModule
import com.amachikhin.testapplication.features.base.presentation.ScreenScope
import com.amachikhin.testapplication.features.base.presentation.ViewModelKey
import com.amachikhin.testapplication.features.currentweather.presentation.CurrentWeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CurrentWeatherViewModelModule : ViewModelModule() {
    @ScreenScope
    @Binds
    @IntoMap
    @ViewModelKey(CurrentWeatherViewModel::class)
    abstract fun bindsCurrentWeatherViewModel(featureFlagsViewModel: CurrentWeatherViewModel): ViewModel
}