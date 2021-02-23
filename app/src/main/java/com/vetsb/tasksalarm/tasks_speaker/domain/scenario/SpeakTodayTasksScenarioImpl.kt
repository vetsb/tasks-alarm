package com.vetsb.tasksalarm.tasks_speaker.domain.scenario

import com.vetsb.tasksalarm.analytics.domain.AnalyticsInteractor
import com.vetsb.tasksalarm.core.domain.UtilInteractor
import com.vetsb.tasksalarm.speaker.domain.usecase.start.StartSpeakingTextUseCase
import com.vetsb.tasksalarm.tasks.domain.usecase.get_today_tasks.GetTodayTasksUseCase
import com.vetsb.tasksalarm.tasks_speaker.TasksSpeakerAnalyticsConstant
import com.vetsb.tasksalarm.tasks_speaker.domain.usecase.convert_tasks_to_ssml.ConvertTasksToSsmlUseCase
import com.vetsb.tasksalarm.tasks_speaker.domain.usecase.hide_notification.TasksSpeakerHideNotificationUseCase
import com.vetsb.tasksalarm.tasks_speaker.domain.usecase.show_notification.TasksSpeakerShowNotificationUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SpeakTodayTasksScenarioImpl(
    private val getTodayTasksUseCase: GetTodayTasksUseCase,
    private val convertTasksToSsmlUseCase: ConvertTasksToSsmlUseCase,
    private val startSpeakingTextUseCase: StartSpeakingTextUseCase,
    private val tasksSpeakerShowNotificationUseCase: TasksSpeakerShowNotificationUseCase,
    private val tasksSpeakerHideNotificationUseCase: TasksSpeakerHideNotificationUseCase,
    private val utilInteractor: UtilInteractor,
    private val analyticsInteractor: AnalyticsInteractor
) : SpeakTodayTasksScenario {

    override suspend fun invoke() {
        withContext(Dispatchers.IO) {
            val tasks = getTodayTasksUseCase()
            val ssml = convertTasksToSsmlUseCase(tasks)
            val text = utilInteractor.extractTextFromHtml(ssml)

            analyticsInteractor.trackEvent(
                TasksSpeakerAnalyticsConstant.EVENT_NAME,
                TasksSpeakerAnalyticsConstant.TEXT_TO_SSML_IS_CONVERTED,
                TasksSpeakerAnalyticsConstant.SSML_LENGTH to ssml.length,
                TasksSpeakerAnalyticsConstant.TEXT_LENGTH to text.length
            )

            tasksSpeakerShowNotificationUseCase()
            startSpeakingTextUseCase(ssml)
            tasksSpeakerHideNotificationUseCase()
        }
    }
}