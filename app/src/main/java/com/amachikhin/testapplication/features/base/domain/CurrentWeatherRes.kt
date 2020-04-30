package com.amachikhin.testapplication.features.base.domain

data class CurrentWeatherRes(
    val id: Int,
    val name: String,
    val icon: String,
    val temp: Double,
    val feelsLike: Double
)
