package com.vetsb.tasksalarm.core.domain

interface UtilRepository {
    fun extractTextFromHtml(html: String): String
}