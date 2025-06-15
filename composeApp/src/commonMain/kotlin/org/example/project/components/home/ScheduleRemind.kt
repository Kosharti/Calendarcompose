package org.example.project.components.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.calendar_icon
import kotlinproject.composeapp.generated.resources.time_icon
import org.example.project.SharedViewModel
import org.example.project.data.Task
import org.example.project.theme.InterFontFamily
import org.jetbrains.compose.resources.painterResource
import java.time.LocalDate

@Composable
fun RemindersScreen(
    viewModel: SharedViewModel,
    date: LocalDate,
    modifier: Modifier = Modifier
) {
    val tasks by remember(date, viewModel.tasksState) {
        derivedStateOf { viewModel.getTasksFor(date.plusDays(1)) }
    }

    Column(modifier = modifier) {
        TomorrowReminderHeader()

        if (tasks.isNotEmpty()) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(tasks, key = { it.title + it.starttime }) { task ->
                    ReminderItem(task = task)
                }
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No reminders for tomorrow")
            }
        }
    }
}

@Composable
private fun TomorrowReminderHeader() {
    Column {
        Text(
            text = "Reminder",
            fontSize = 20.sp,
            fontFamily = InterFontFamily(),
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1E293B)
        )
        Text(
            text = "Don't forget schedule for tomorrow",
            fontSize = 14.sp,
            fontFamily = InterFontFamily(),
            color = Color(0xFF64748B),
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun ReminderItem(task: Task) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF8572FF), shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(Res.drawable.calendar_icon),
            contentDescription = "Calendar",
            modifier = Modifier.size(40.dp)
        )

        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(
                text = task.title,
                fontSize = 14.sp,
                fontFamily = InterFontFamily(),
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            if (task.note.isNotBlank()) {
                Text(
                    text = task.note,
                    fontSize = 13.sp,
                    fontFamily = InterFontFamily(),
                    color = Color.White.copy(alpha = 0.8f),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            ReminderTime(time = "${task.starttime} - ${task.endtime}")
        }
    }
}

@Composable
private fun ReminderTime(time: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 8.dp)
    ) {
        Image(
            painter = painterResource(Res.drawable.time_icon),
            contentDescription = "Time",
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = time,
            fontSize = 13.sp,
            fontFamily = InterFontFamily(),
            color = Color.White,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

