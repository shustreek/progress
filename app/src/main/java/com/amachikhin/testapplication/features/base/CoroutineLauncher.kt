package com.amachikhin.testapplication.features.base

import kotlinx.coroutines.*

interface CoroutineLauncher : CoroutineScope {

    fun CoroutineLauncher.launchSafely(
        onLoading: (Boolean) -> Unit,
        onError: ((Throwable) -> Unit)? = null,
        block: suspend CoroutineScope.() -> Unit
    ): Job = launch {
        var isCancellation = false
        onLoading(true)
        try {
            block()
        } catch (error: Throwable) {
            isCancellation = error is CancellationException
            if (!isActive) return@launch

            onError?.invoke(error)

        } finally {
            if (!isCancellation) onLoading(false)
        }
    }

}
