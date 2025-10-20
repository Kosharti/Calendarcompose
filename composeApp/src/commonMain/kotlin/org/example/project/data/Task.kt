package org.example.project.data

import java.time.LocalDate
import java.time.LocalTime

data class Task(
    val title: String,
    val starttime: String,
    val endtime: String,
    val date: LocalDate,
    val note: String = "",
    val notificationId: Int = 0
)

data class TasksState(
    val allTasks: List<Task> = emptyList(),
    val showTomorrowOnly: Boolean = false
)