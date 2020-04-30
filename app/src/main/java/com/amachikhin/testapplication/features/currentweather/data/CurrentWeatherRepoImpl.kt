package com.amachikhin.testapplication.features.currentweather.data

import com.amachikhin.testapplication.Units
import com.amachikhin.testapplication.api.Api
import com.amachikhin.testapplication.features.base.data.RepositoryImpl
import com.amachikhin.testapplication.features.base.domain.CurrentWeatherRes
import com.amachikhin.testapplication.features.currentweather.domain.mapper.CurrentWeatherMapper
import com.amachikhin.testapplication.storage.dao.CurrentWeatherDao
import com.amachikhin.testapplication.storage.entity.CurrentWeatherEntity
import javax.inject.Inject

class CurrentWeatherRepoImpl @Inject constructor(
    private val api: Api,
    private val dataBase: CurrentWeatherDao
) : RepositoryImpl(), CurrentWeatherRepo {

    override suspend fun loadWeather(cityId: Long) = ioAsync {
        CurrentWeatherMapper().map(api.getWeather(cityId, Units.METRIC)).apply {
            //todo Сделать маппер
            dataBase.insert(
                CurrentWeatherEntity(id, name, icon, temp, feelsLike)
            )
        }
    }

    override suspend fun getWeather(cityId: Long): CurrentWeatherRes? = ioAsync {
        dataBase.getWeather(cityId)?.run {
            //todo Сделать маппер
            CurrentWeatherRes(id, name, icon, temp, feelsLike)
        }
    }
}
