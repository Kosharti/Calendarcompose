package org.example.project

import android.content.Context

actual class NotificationService actual constructor(private val context: Any?) {
    actual fun showNotification(title: String, message: String) {
        if (context is Context) {
            NotificationUtil(context).showNotification(title, message)
        }
    }

    actual fun showReminderNotification(title: String, message: String) {
        if (context is Context) {
            NotificationUtil(context).showReminderNotification(title, message)
        }
    }
}