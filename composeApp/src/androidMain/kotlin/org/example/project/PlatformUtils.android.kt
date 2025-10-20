package org.example.project

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.example.project.data.Task
import java.time.LocalDateTime

actual suspend fun scheduleAndroidReminder(context: Any, task: Task, taskDateTime: LocalDateTime) {
    withContext(Dispatchers.IO) {
        if (context is Context) {
            AndroidNotificationScheduler.scheduleReminder(context, task, taskDateTime)
        }
    }
}

actual fun isAndroidContext(context: Any?): Boolean {
    return context is Context
}