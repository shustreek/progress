package com.amachikhin.testapplication.features.currentweather.data

import com.amachikhin.testapplication.features.base.data.Repository
import com.amachikhin.testapplication.features.base.domain.CurrentWeatherRes

interface CurrentWeatherRepo : Repository {
    suspend fun loadWeather(cityId: Long): CurrentWeatherRes
    suspend fun getWeather(cityId: Long): CurrentWeatherRes?
}
