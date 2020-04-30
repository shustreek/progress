package com.amachikhin.testapplication.features.currentweather.domain

import com.amachikhin.testapplication.features.base.domain.Interactor

interface CurrentWeatherInteractor : Interactor<CurrentWeatherInteractorOut> {
    fun loadWeather()
}
