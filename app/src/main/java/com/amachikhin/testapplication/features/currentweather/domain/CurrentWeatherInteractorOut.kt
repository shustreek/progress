package com.amachikhin.testapplication.features.currentweather.domain

import com.amachikhin.testapplication.features.base.domain.CurrentWeatherRes
import com.amachikhin.testapplication.features.base.domain.InteractorOut

interface CurrentWeatherInteractorOut : InteractorOut {

    fun isLoading(loading: Boolean)
    fun onLoaded(weather: CurrentWeatherRes)
    fun onError(e: Throwable)
}
