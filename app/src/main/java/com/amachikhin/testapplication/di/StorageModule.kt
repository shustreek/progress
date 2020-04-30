package com.amachikhin.testapplication.di

import android.content.Context
import androidx.room.Room
import com.amachikhin.testapplication.storage.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "database")
            .build()
}
