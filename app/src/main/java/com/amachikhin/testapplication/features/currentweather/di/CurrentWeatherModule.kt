package com.amachikhin.testapplication.features.currentweather.di

import com.amachikhin.testapplication.api.Api
import com.amachikhin.testapplication.features.base.presentation.ScreenScope
import com.amachikhin.testapplication.features.currentweather.data.CurrentWeatherRepo
import com.amachikhin.testapplication.features.currentweather.data.CurrentWeatherRepoImpl
import com.amachikhin.testapplication.features.currentweather.domain.CurrentWeatherInteractor
import com.amachikhin.testapplication.features.currentweather.domain.CurrentWeatherInteractorImpl
import com.amachikhin.testapplication.storage.AppDatabase
import com.amachikhin.testapplication.storage.dao.CurrentWeatherDao
import dagger.Module
import dagger.Provides

@Module
class CurrentWeatherModule {

    @ScreenScope
    @Provides
    fun provideCurrentWeatherDao(appDatabase: AppDatabase): CurrentWeatherDao =
        appDatabase.currentWeatherDao()

    @ScreenScope
    @Provides
    fun provideCurrentWeatherRepo(api: Api, dataBase:CurrentWeatherDao): CurrentWeatherRepo =
        CurrentWeatherRepoImpl(api, dataBase)

    @ScreenScope
    @Provides
    fun provideCurrentWeatherInteractor(repo: CurrentWeatherRepo): CurrentWeatherInteractor =
        CurrentWeatherInteractorImpl(repo)

}
