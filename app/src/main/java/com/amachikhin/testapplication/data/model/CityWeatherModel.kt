package com.amachikhin.testapplication.data.model

import com.google.gson.annotations.SerializedName

data class CityWeatherModel(
    @SerializedName("coord") val coord: CoordModel,
    @SerializedName("weather") val weather: List<WeatherModel>,
    @SerializedName("base") val base: String,
    @SerializedName("main") val main: MainModel,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("wind") val wind: WindModel,
    @SerializedName("clouds") val clouds: CloudsModel,
    @SerializedName("dt") val dt: Int,
    @SerializedName("sys") val sys: SysModel,
    @SerializedName("timezone") val timezone: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("cod") val cod: Int
) : ResponseModel()