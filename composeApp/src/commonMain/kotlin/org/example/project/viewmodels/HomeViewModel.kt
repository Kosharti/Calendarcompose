package org.example.project.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.data.Task
import java.time.LocalDate

class HomeViewModel : ViewModel() {
    private val _selectedDate = MutableStateFlow(LocalDate.now())
    val selectedDate = _selectedDate.asStateFlow()

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks = _tasks.asStateFlow()

    fun selectDate(date: LocalDate) {
        _selectedDate.value = date
    }

    fun getTasksFor(date: LocalDate): List<Task> {
        return _tasks.value.filter { it.date == date }
            .sortedBy { it.starttime }
    }

    fun getTomorrowTasks(): List<Task> {
        val tomorrow = LocalDate.now().plusDays(1)
        return _tasks.value.filter { it.date == tomorrow }
    }

    fun addTasks(newTasks: List<Task>) {
        _tasks.value = _tasks.value + newTasks
    }
}