package com.amachikhin.testapplication.features.base.domain

import com.amachikhin.testapplication.features.base.CoroutineLauncher
import com.amachikhin.testapplication.features.base.CoroutineScopeImpl

abstract class InteractorImpl<T : InteractorOut> : Interactor<T>, CoroutineScopeImpl(), CoroutineLauncher {

    protected lateinit var out: T

    override fun setupInteractorOut(out: T) {
        this.out = out
    }

}
