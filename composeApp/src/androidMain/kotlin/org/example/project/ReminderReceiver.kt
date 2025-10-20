package org.example.project

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val title = intent.getStringExtra("title") ?: "Напоминание"
        val message = intent.getStringExtra("message") ?: "Время выполнить задачу!"

        Log.d("🔔 ReminderReceiver", "=== RECEIVED REMINDER ===")
        Log.d("🔔 ReminderReceiver", "Title: $title")
        Log.d("🔔 ReminderReceiver", "Message: $message")
        Log.d("🔔 ReminderReceiver", "Current system time: ${System.currentTimeMillis()}")
        Log.d("🔔 ReminderReceiver", "Current DateTime: ${LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)}")

        NotificationUtil(context).showReminderNotification(title, message)

        Log.d("🔔 ReminderReceiver", "Notification shown successfully")
    }
}