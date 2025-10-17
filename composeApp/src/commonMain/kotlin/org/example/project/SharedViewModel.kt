package org.example.project

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.data.Task
import org.example.project.data.TasksState
import java.time.LocalDate
import java.time.LocalTime

class SharedViewModel : ViewModel() {
    //val name = MutableStateFlow("Shuri")
    val tasksState = MutableStateFlow(TasksState())
    val notifyActive = MutableStateFlow(false)
    val message = MutableStateFlow("")

    fun showNotification(text: String) {
        viewModelScope.launch {
            message.value = text
            notifyActive.value = true
            delay(4000)
            notifyActive.value = false
        }
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
        showNotification("Added: ${newTask.title}")
        return true
    }

    fun getTasksFor(date: LocalDate): List<Task> {
        return tasksState.value.allTasks
            .filter { it.date == date }
            .sortedBy { LocalTime.parse(it.starttime) }
    }
}