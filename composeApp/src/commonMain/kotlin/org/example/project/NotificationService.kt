package org.example.project

expect class NotificationService(context: Any?) {
    fun showNotification(title: String, message: String)
    fun showReminderNotification(title: String, message: String)
}