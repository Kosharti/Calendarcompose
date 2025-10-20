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
            // Здесь будет вызов системы уведомлений
            // Для демонстрации просто показываем уведомление в UI
            // В реальном приложении здесь будет WorkManager или AlarmManager
            showTaskReminderNotification(task)
        }
    }

    private fun showTaskReminderNotification(task: Task) {
        // В реальном приложении здесь будет вызов системы уведомлений
        // Для Compose Desktop можно показать системное уведомление
        // Для Android - использовать NotificationCompat
        println("REMINDER: ${task.title} at ${task.starttime}")
    }
}