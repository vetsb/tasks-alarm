package com.vetsb.tasksalarm.tasks

import com.vetsb.tasksalarm.tasks.data.ticktick.TickTickTasksRepositoryImpl
import com.vetsb.tasksalarm.tasks.domain.TasksRepository
import com.vetsb.tasksalarm.tasks.domain.usecase.get_today_tasks.GetTodayTasksUseCase
import com.vetsb.tasksalarm.tasks.domain.usecase.get_today_tasks.GetTodayTasksUseCaseImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val tasksModule = module {
    single { TickTickTasksRepositoryImpl(get()) } bind TasksRepository::class

    factory { GetTodayTasksUseCaseImpl(get<TickTickTasksRepositoryImpl>()) } bind GetTodayTasksUseCase::class
}