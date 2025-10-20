package org.example.project

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.NotificationCompat
import kotlin.random.Random

@Composable
actual fun Notify(message: String) {
    val context = LocalContext.current
    val notificationUtil = remember { NotificationUtil(context) }

    LaunchedEffect(message) {
        notificationUtil.showNotification("Новая задача", message)
    }
}

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
            setSound(android.media.RingtoneManager.getDefaultUri(android.media.RingtoneManager.TYPE_ALARM), null)
        }

        val manager = context.getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(regularChannel)
        manager.createNotificationChannel(reminderChannel)
    }

    fun showNotification(title: String, message: String) {
        val notificationId = Random.nextInt(1000, 9999)

        val builder = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setContentText(message)
            .setVibrate(longArrayOf(100, 200, 100))
            .setSmallIcon(R.drawable.ic_task_notification)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        val manager = context.getSystemService(NotificationManager::class.java)
        manager.notify(notificationId, builder.build())
    }

    fun showReminderNotification(title: String, message: String) {
        val notificationId = Random.nextInt(1000, 9999)

        val builder = NotificationCompat.Builder(context, reminderChannelId)
            .setContentTitle(title)
            .setContentText(message)
            .setVibrate(longArrayOf(500, 500, 500))
            .setSmallIcon(R.drawable.ic_task_notification)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setTimeoutAfter(60000)

        val manager = context.getSystemService(NotificationManager::class.java)
        manager.notify(notificationId, builder.build())
    }
}