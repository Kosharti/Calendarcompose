package org.example.project

import org.example.project.data.Task
import java.time.LocalDateTime

actual suspend fun scheduleAndroidReminder(context: Any, task: Task, taskDateTime: LocalDateTime) {
}

actual fun isAndroidContext(context: Any?): Boolean {
    return false
}