package org.example.project

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.example.project.data.Task

expect suspend fun scheduleAndroidReminder(context: Any, task: Task, taskDateTime: java.time.LocalDateTime)

expect fun isAndroidContext(context: Any?): Boolean