package org.example.project.components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.avatars_icon
import org.example.project.SharedViewModel
import org.example.project.data.Task
import org.example.project.theme.InterFontFamily
import org.jetbrains.compose.resources.painterResource
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun ScheduleToday(
    viewModel: SharedViewModel,
    date: LocalDate,
    modifier: Modifier = Modifier
) {
    val tasks by remember(date, viewModel.tasksState) {
        derivedStateOf {
            viewModel.getTasksFor(date)
                .sortedBy { LocalTime.parse(it.starttime).toSecondOfDay() }
        }
    }

    Column(
        modifier = modifier
    ) {
        Text(
            text = "Schedule Today",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = InterFontFamily(),
            color = Color(0xFF1E293B),
            letterSpacing = 0.3.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        if (tasks.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(tasks, key = { it.title + it.starttime }) { task ->
                    ScheduleItemRow(task = task)
                }
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No tasks for today")
            }
        }
    }
}
@Composable
private fun ScheduleItemRow(task: Task) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(end = 8.dp)
        ) {
            Text(
                text = task.starttime,
                color = Color(0xFF64748B),
                fontFamily = InterFontFamily(),
                fontSize = 14.sp
            )
            Text(
                text = task.endtime,
                color = Color(0xFF64748B),
                fontFamily = InterFontFamily(),
                fontSize = 14.sp
            )
        }

        ScheduleItem(task = task)
    }
}

@Composable
fun ScheduleItem(task: Task, onClick: () -> Unit = {}) {
    val categoryColors = remember {
        mapOf(
            "Meeting" to Color(0xFFF59E0B),
            "Hangout" to Color(0xFF701A75),
            "Cooking" to Color(0xFFDC2626),
            "Other" to Color(0xFF4A4A4A),
            "Weekend" to Color(0xFF1A7529)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFDE496E), shape = RoundedCornerShape(16.dp))
            .padding(12.dp)
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .background(
                            color = categoryColors[task.title] ?: Color.Gray,
                            shape = CircleShape
                        )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = task.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontFamily = InterFontFamily(),
                    modifier = Modifier.weight(1f)
                )
            }

            if (task.note.isNotBlank()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = task.note,
                    fontSize = 12.sp,
                    maxLines = 2,
                    color = Color.White.copy(alpha = 0.8f),
                    fontFamily = InterFontFamily(),
                    modifier = Modifier.padding(start = 10.dp, end = 30.dp)
                )
            }
        }

        Image(
            painter = painterResource(Res.drawable.avatars_icon),
            contentDescription = "avatars",
            modifier = Modifier
                .size(35.dp)
                .align(Alignment.CenterEnd)
        )
    }
}