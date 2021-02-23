package com.vetsb.tasksalarm.appconfig.domain.usecase.initialize

import com.vetsb.tasksalarm.appconfig.domain.AppConfigRepository

class InitializeAppConfigUseCaseImpl(
    private val appConfigRepository: AppConfigRepository
) : InitializeAppConfigUseCase {

    override suspend fun invoke() {
        appConfigRepository.initialize()
    }
}