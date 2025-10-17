package org.example.project.data

import java.time.LocalDate

data class Task(
    val title: String,
    val starttime: String,
    val endtime: String,
    val date: LocalDate,
    val note: String = ""
)

data class TasksState(
    val allTasks: List<Task> = emptyList(),
    val showTomorrowOnly: Boolean = false
)