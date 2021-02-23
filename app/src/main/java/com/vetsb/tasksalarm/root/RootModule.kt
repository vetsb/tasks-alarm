package com.vetsb.tasksalarm.root

import com.vetsb.tasksalarm.root.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val rootModule = module {
    viewModel { MainViewModel(get(), get()) }
}