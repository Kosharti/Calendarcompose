package org.example.project

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.data.Task
import org.example.project.data.TasksState
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class SharedViewModel : ViewModel() {
    val tasksState = MutableStateFlow(TasksState())

    private var notificationService: NotificationService? = null

    // Для Android версии
    fun initializeWithContext(context: Any) {
        notificationService = NotificationService(context)
    }

    // Для Desktop версии
    fun initializeForDesktop() {
        notificationService = NotificationService(null)
    }

    fun addTask(newTask: Task): Boolean {
        val newStart = LocalTime.parse(newTask.starttime)
        val newEnd = LocalTime.parse(newTask.endtime)

        if (newStart >= newEnd) return false

        val hasConflict = tasksState.value.allTasks.any { task ->
            task.date == newTask.date &&
                    LocalTime.parse(task.starttime) < newEnd &&
                    LocalTime.parse(task.endtime) > newStart
        }

        if (hasConflict) return false

        tasksState.update { current ->
            current.copy(allTasks = current.allTasks + newTask)
        }

        scheduleTaskNotification(newTask)
        showTaskCreationNotification(newTask) // ВЫЗОВ СИСТЕМНОГО УВЕДОМЛЕНИЯ
        return true
    }

    private fun scheduleTaskNotification(task: Task) {
        viewModelScope.launch {
            val taskDateTime = LocalDateTime.of(task.date, LocalTime.parse(task.starttime))
            NotificationManager(notificationService).scheduleTaskNotification(task, taskDateTime)
        }
    }

    private fun showTaskCreationNotification(task: Task) {
        // ПРЯМОЙ ВЫЗОВ СИСТЕМНОГО УВЕДОМЛЕНИЯ
        notificationService?.showNotification(
            "Добавлена задача: ${task.title}",
            "Время: ${task.starttime} - ${task.endtime}"
        )
    }

    fun getTasksFor(date: LocalDate): List<Task> {
        return tasksState.value.allTasks
            .filter { it.date == date }
            .sortedBy { LocalTime.parse(it.starttime) }
    }

    fun getAllTasks(): List<Task> = tasksState.value.allTasks

    fun setAllTasks(tasks: List<Task>) {
        tasksState.update { current ->
            current.copy(allTasks = tasks)
        }

        tasks.forEach { scheduleTaskNotification(it) }
    }
}