package com.amachikhin.testapplication.api

import com.amachikhin.testapplication.data.model.CityWeatherModel
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("weather")
    suspend fun getWeather(@Query("id") id: Long, @Query("units") units: String): CityWeatherModel
}