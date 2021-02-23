package com.vetsb.tasksalarm.appconfig.domain.usecase.get_config

import com.vetsb.tasksalarm.appconfig.domain.model.AppConfig

interface GetAppConfigUseCase {
    operator fun invoke(): AppConfig
}