package com.amachikhin.testapplication.features.base.domain

import com.amachikhin.testapplication.features.base.Cancellable

interface Interactor<T : InteractorOut> : Cancellable {

    fun setupInteractorOut(out: T)

}
