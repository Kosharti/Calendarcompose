package org.example.project

import kotlinx.coroutines.delay
import org.example.project.data.Task
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class NotificationManager(private val notificationService: NotificationService?) {

    suspend fun scheduleTaskNotification(task: Task, taskDateTime: LocalDateTime) {
        val now = LocalDateTime.now()
        val delayMillis = ChronoUnit.MILLIS.between(now, taskDateTime)

        if (delayMillis > 0) {
            delay(delayMillis)
            showTaskReminderNotification(task) // ВЫЗОВ СИСТЕМНОГО УВЕДОМЛЕНИЯ
        }
    }

    private fun showTaskReminderNotification(task: Task) {
        // ПРЯМОЙ ВЫЗОВ СИСТЕМНОГО УВЕДОМЛЕНИЯ
        notificationService?.showReminderNotification(
            "Напоминание: ${task.title}",
            "Время: ${task.starttime} - ${task.endtime}\n${task.note}"
        )
    }
}