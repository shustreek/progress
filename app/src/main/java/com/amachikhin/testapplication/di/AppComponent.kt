package com.amachikhin.testapplication.di

import android.content.Context
import com.amachikhin.testapplication.api.Api
import com.amachikhin.testapplication.storage.AppDatabase
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, NetworkModule::class, StorageModule::class])
@Singleton
interface AppComponent {

    fun context(): Context
    fun api(): Api
    fun databse(): AppDatabase

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun context(context: Context): Builder
    }
}