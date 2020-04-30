package com.amachikhin.testapplication.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amachikhin.testapplication.storage.dao.CurrentWeatherDao
import com.amachikhin.testapplication.storage.entity.CurrentWeatherEntity

@Database(version = 1, exportSchema = false, entities = [CurrentWeatherEntity::class])
//@TypeConverters(DateConverter.class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherDao
}