package com.vetsb.tasksalarm.core.domain

class UtilInteractor(
    private val utilRepository: UtilRepository
) : UtilRepository by utilRepository