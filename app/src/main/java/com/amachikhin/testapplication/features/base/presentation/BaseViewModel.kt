package com.amachikhin.testapplication.features.base.presentation;

import androidx.lifecycle.ViewModel
import com.amachikhin.testapplication.features.base.domain.Interactor
import com.amachikhin.testapplication.features.base.domain.InteractorOut

abstract class BaseViewModel<T : Interactor<out InteractorOut>>(protected val interactor: T) : ViewModel(), InteractorOut {

    open fun onStart() {
        //empty
    }

    open fun onCreate() {
        //empty
    }

    open fun onStop() {
        //empty
    }

    override fun onCleared() {
        interactor.cancel()
    }
}