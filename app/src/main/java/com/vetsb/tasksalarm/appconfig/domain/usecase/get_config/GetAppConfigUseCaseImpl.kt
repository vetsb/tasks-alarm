package com.vetsb.tasksalarm.appconfig.domain.usecase.get_config

import com.vetsb.tasksalarm.appconfig.domain.AppConfigRepository
import com.vetsb.tasksalarm.appconfig.domain.model.AppConfig

class GetAppConfigUseCaseImpl(
    private val appConfigRepository: AppConfigRepository
) : GetAppConfigUseCase {

    override fun invoke(): AppConfig {
        return appConfigRepository.appConfig
    }
}