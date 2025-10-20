package org.example.project.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.example.project.SharedViewModel
import org.example.project.components.SetScheduleButton
import org.example.project.components.home.Calendar
import org.example.project.components.home.Home
import org.example.project.components.home.RemindersScreen
import org.example.project.components.home.ScheduleToday
import org.example.project.viewmodels.HomeViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import java.time.LocalDate

@Composable
@Preview
fun HomeScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    modifier: Modifier = Modifier
) {
    val homeViewModel: HomeViewModel = viewModel()

    LaunchedEffect(sharedViewModel.tasksState.value.allTasks) {
        homeViewModel.addTasks(sharedViewModel.tasksState.value.allTasks)
    }

    val selectedDate by homeViewModel.selectedDate.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Home()

        Calendar(
            selectedDate = selectedDate,
            onDateSelected = { homeViewModel.selectDate(it) },
            modifier = Modifier.fillMaxWidth()
        )

        ScheduleToday(
            viewModel = sharedViewModel,
            date = selectedDate,
            modifier = Modifier.weight(1f)
        )

        RemindersScreen(
            viewModel = sharedViewModel,
            date = selectedDate,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.height(16.dp))
        SetScheduleButton(
            text = "Set Schedule",
            onClick = { navController.navigate("schedule") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}