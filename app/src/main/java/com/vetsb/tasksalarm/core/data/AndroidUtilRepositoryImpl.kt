package com.vetsb.tasksalarm.core.data

import androidx.core.text.HtmlCompat
import com.vetsb.tasksalarm.core.domain.UtilRepository

class AndroidUtilRepositoryImpl : UtilRepository {

    override fun extractTextFromHtml(html: String): String {
        return HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
    }
}