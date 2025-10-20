package org.example.project.components.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.example.project.SharedViewModel
import org.example.project.viewmodels.ScheduleViewModel

@Composable
fun TaskSet(
    sharedViewModel: SharedViewModel,
    navController: NavController
) {
    val scheduleViewModel: ScheduleViewModel = viewModel()

    val selectedDate by scheduleViewModel.selectedDate.collectAsState()
    val startHour by scheduleViewModel.startHour.collectAsState()
    val startMinute by scheduleViewModel.startMinute.collectAsState()
    val endHour by scheduleViewModel.endHour.collectAsState()
    val endMinute by scheduleViewModel.endMinute.collectAsState()
    val note by scheduleViewModel.note.collectAsState()
    val selectedCategory by scheduleViewModel.selectedCategory.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        DateSelect(
            selectedDate = selectedDate,
            onDateSelected = { scheduleViewModel.selectDate(it) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TimeSelect(
            startHour = startHour,
            startMinute = startMinute,
            endHour = endHour,
            endMinute = endMinute,
            onStartHourChanged = { scheduleViewModel.setStartHour(it) },
            onStartMinuteChanged = { scheduleViewModel.setStartMinute(it) },
            onEndHourChanged = { scheduleViewModel.setEndHour(it) },
            onEndMinuteChanged = { scheduleViewModel.setEndMinute(it) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Category(
            selectedCategory = selectedCategory,
            onCategorySelected = { category ->
                scheduleViewModel.selectCategory(category)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        InputNote(
            note = note,
            onNoteChanged = { scheduleViewModel.setNote(it) },
            onSaveClicked = {
                val task = scheduleViewModel.createTask()
                if (task != null && sharedViewModel.addTask(task)) {
                    scheduleViewModel.resetForm()
                    navController.popBackStack()
                }
            }
        )
    }
}