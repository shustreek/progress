package com.amachikhin.testapplication.di

import androidx.lifecycle.ViewModelProvider
import com.amachikhin.testapplication.features.base.presentation.ScreenScope
import com.amachikhin.testapplication.features.base.presentation.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @ScreenScope
    @Binds
    internal abstract fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
