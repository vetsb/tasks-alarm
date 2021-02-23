package com.vetsb.tasksalarm.core.base.ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun <I, T : Flow<I>> T.observe(lifecycleOwner: LifecycleOwner, action: (I) -> Unit) {
    onEach {
        action(it)
    }.launchIn(lifecycleOwner.lifecycle.coroutineScope)
}