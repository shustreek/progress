package com.amachikhin.testapplication.features.currentweather.domain.mapper

import com.amachikhin.testapplication.data.Mapper
import com.amachikhin.testapplication.data.model.CityWeatherModel
import com.amachikhin.testapplication.features.base.domain.CurrentWeatherRes


class CurrentWeatherMapper : Mapper<CityWeatherModel, CurrentWeatherRes>() {
    override fun map(from: CityWeatherModel): CurrentWeatherRes {
        return CurrentWeatherRes(
            from.id,
            from.name,
            from.weather[0].icon,
            from.main.temp,
            from.main.feels_like
        )
    }
}

