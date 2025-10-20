package org.example.project.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.data.Task
import java.time.LocalDate
import java.util.*

class ScheduleViewModel : ViewModel() {
    private val _selectedDate = MutableStateFlow<LocalDate?>(null)
    val selectedDate = _selectedDate.asStateFlow()

    private val _startHour = MutableStateFlow<Int?>(null)
    val startHour = _startHour.asStateFlow()

    private val _startMinute = MutableStateFlow<Int?>(null)
    val startMinute = _startMinute.asStateFlow()

    private val _endHour = MutableStateFlow<Int?>(null)
    val endHour = _endHour.asStateFlow()

    private val _endMinute = MutableStateFlow<Int?>(null)
    val endMinute = _endMinute.asStateFlow()

    private val _note = MutableStateFlow("")
    val note = _note.asStateFlow()

    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory = _selectedCategory.asStateFlow()

    fun selectDate(date: LocalDate) {
        _selectedDate.value = date
    }

    fun setStartHour(hour: Int) {
        _startHour.value = hour
    }

    fun setStartMinute(minute: Int) {
        _startMinute.value = minute
    }

    fun setEndHour(hour: Int) {
        _endHour.value = hour
    }

    fun setEndMinute(minute: Int) {
        _endMinute.value = minute
    }

    fun setNote(text: String) {
        _note.value = text
    }

    fun selectCategory(category: String) {
        _selectedCategory.value = category
    }

    fun createTask(): Task? {
        val date = _selectedDate.value ?: return null
        val startH = _startHour.value ?: return null
        val startM = _startMinute.value ?: return null
        val endH = _endHour.value ?: return null
        val endM = _endMinute.value ?: return null
        val category = _selectedCategory.value ?: return null
        val taskNote = _note.value

        val startTime = "%02d:%02d".format(startH, startM)
        val endTime = "%02d:%02d".format(endH, endM)

        return Task(
            title = category,
            starttime = startTime,
            endtime = endTime,
            date = date,
            note = taskNote,
            notificationId = Random().nextInt(10000)
        )
    }

    fun resetForm() {
        _selectedDate.value = null
        _startHour.value = null
        _startMinute.value = null
        _endHour.value = null
        _endMinute.value = null
        _note.value = ""
        _selectedCategory.value = null
    }
}