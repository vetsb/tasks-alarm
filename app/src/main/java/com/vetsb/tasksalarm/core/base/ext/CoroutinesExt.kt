package com.vetsb.tasksalarm.core.base.ext

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import timber.log.Timber
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun createExceptionHandler(exceptionBlock: ((Throwable) -> Unit)? = null): CoroutineExceptionHandler {
    return CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        exceptionBlock?.invoke(exception)
    }
}

fun CoroutineScope.launchSafe(
    context: CoroutineContext = EmptyCoroutineContext,
    onError: (Throwable) -> Unit = {},
    action: suspend () -> Unit
): Job {
    return launch(context + createExceptionHandler(onError)) {
        action.invoke()
    }
}

fun Flow<*>.launchSafeIn(scope: CoroutineScope, onError: (Throwable) -> Unit = {}): Job {
    return launchIn(scope + createExceptionHandler(onError))
}

fun Job?.isCompletedOrCanceled(): Boolean {
    return this == null || isCompleted || isCancelled
}

val processLifecycleScope: LifecycleCoroutineScope
    inline get() = ProcessLifecycleOwner.get().lifecycle.coroutineScope