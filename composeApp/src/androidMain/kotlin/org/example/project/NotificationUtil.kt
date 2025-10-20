package org.example.project

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import kotlin.random.Random

class NotificationUtil(private val context: Context) {
    private val channelId = "task_channel_01"
    private val reminderChannelId = "task_reminder_channel"

    init {
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        val regularChannel = NotificationChannel(
            channelId,
            "Уведомления о задачах",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "Канал для уведомлений о создании задач"
            enableVibration(true)
            vibrationPattern = longArrayOf(100, 200, 100)
        }

        val reminderChannel = NotificationChannel(
            reminderChannelId,
            "Напоминания о задачах",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Канал для напоминаний о начале задач"
            enableVibration(true)
            vibrationPattern = longArrayOf(500, 500, 500)
        }

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(regularChannel)
        manager.createNotificationChannel(reminderChannel)
    }

    fun showNotification(title: String, message: String) {
        val notificationId = Random.nextInt(1000, 9999)

        val builder = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(notificationId, builder.build())
    }

    fun showReminderNotification(title: String, message: String) {
        val notificationId = Random.nextInt(1000, 9999)

        val builder = NotificationCompat.Builder(context, reminderChannelId)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(notificationId, builder.build())
    }
}