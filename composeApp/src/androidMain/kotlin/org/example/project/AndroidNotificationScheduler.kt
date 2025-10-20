package org.example.project

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import org.example.project.data.Task
import java.time.LocalDateTime
import java.time.ZoneId

class AndroidNotificationScheduler {

    companion object {
        fun scheduleReminder(context: Context, task: Task, taskDateTime: LocalDateTime) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

            val triggerTime = taskDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

            Log.d("🔔 DEBUG", "=== SCHEDULING REMINDER ===")
            Log.d("🔔 DEBUG", "Task: '${task.title}'")
            Log.d("🔔 DEBUG", "Task date: ${task.date}")
            Log.d("🔔 DEBUG", "Task start time: ${task.starttime}")
            Log.d("🔔 DEBUG", "Calculated DateTime: $taskDateTime")
            Log.d("🔔 DEBUG", "Trigger time (ms): $triggerTime")
            Log.d("🔔 DEBUG", "Current time (ms): ${System.currentTimeMillis()}")
            Log.d("🔔 DEBUG", "Delay (ms): ${triggerTime - System.currentTimeMillis()}")
            Log.d("🔔 DEBUG", "Delay (minutes): ${(triggerTime - System.currentTimeMillis()) / 1000 / 60} min")

            val intent = Intent(context, ReminderReceiver::class.java).apply {
                putExtra("title", "Напоминание: ${task.title}")
                putExtra("message", "Время: ${task.starttime} - ${task.endtime}\n${task.note}")
                putExtra("notificationId", task.notificationId)
            }

            val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            } else {
                PendingIntent.FLAG_UPDATE_CURRENT
            }

            val pendingIntent = PendingIntent.getBroadcast(
                context,
                task.notificationId,
                intent,
                flags
            )

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    triggerTime,
                    pendingIntent
                )
            } else {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
            }

            Log.d("🔔 DEBUG", "Reminder scheduled successfully!")
        }
    }
}