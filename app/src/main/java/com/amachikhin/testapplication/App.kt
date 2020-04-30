package com.amachikhin.testapplication

import android.app.Application
import com.amachikhin.testapplication.di.AppComponent
import com.amachikhin.testapplication.di.DaggerAppComponent

class App : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .context(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: App
    }
}