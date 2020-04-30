package com.amachikhin.testapplication.features.currentweather.domain

import com.amachikhin.testapplication.CITY_ID
import com.amachikhin.testapplication.features.base.domain.InteractorImpl
import com.amachikhin.testapplication.features.currentweather.data.CurrentWeatherRepo
import javax.inject.Inject

class CurrentWeatherInteractorImpl @Inject constructor(private val repo: CurrentWeatherRepo) :
    InteractorImpl<CurrentWeatherInteractorOut>(),
    CurrentWeatherInteractor {
    override fun loadWeather() {
        launchSafely(
            { out.isLoading(it) },
            { out.onError(it) }) {
            repo.getWeather(CITY_ID)?.let { out.onLoaded(it) }
            out.onLoaded(repo.loadWeather(CITY_ID))
        }
    }
}
