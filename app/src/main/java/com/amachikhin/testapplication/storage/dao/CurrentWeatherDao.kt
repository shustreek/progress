package com.amachikhin.testapplication.storage.dao

import androidx.room.Dao
import androidx.room.Query
import com.amachikhin.testapplication.storage.entity.CurrentWeatherEntity

@Dao
interface CurrentWeatherDao : BaseDao<CurrentWeatherEntity> {

    @Query("SELECT * FROM current_weather WHERE id =:id")
    fun getWeather(id: Long): CurrentWeatherEntity?
}