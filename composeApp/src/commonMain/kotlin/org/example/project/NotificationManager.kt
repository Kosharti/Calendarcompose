package org.example.project

import kotlinx.coroutines.delay
import org.example.project.data.Task
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class NotificationManager {

    suspend fun scheduleTaskNotification(task: Task, taskDateTime: LocalDateTime) {
        val now = LocalDateTime.now()
        val delayMillis = ChronoUnit.MILLIS.between(now, taskDateTime)

        if (delayMillis > 0) {
            delay(delayMillis)
            showTaskReminderNotification(task)
        }
    }

    private fun showTaskReminderNotification(task: Task) {
        NotificationSystem.showTaskReminder(
            "Напоминание: ${task.title}",
            "Время: ${task.starttime} - ${task.endtime}\n${task.note}"
        )
    }
}

object NotificationSystem {
    private var notificationHandler: ((String, String) -> Unit)? = null

    fun setNotificationHandler(handler: (String, String) -> Unit) {
        notificationHandler = handler
    }

    fun showTaskReminder(title: String, message: String) {
        notificationHandler?.invoke(title, message)
    }
}