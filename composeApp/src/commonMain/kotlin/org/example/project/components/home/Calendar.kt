package org.example.project.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.theme.InterFontFamily
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

@Composable
fun Calendar(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    val today = LocalDate.now()
    val scrollState = rememberScrollState()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState)
            .padding(vertical = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        (-7..7).forEach { offset ->
            val date = today.plusDays(offset.toLong())
            CalendarDay(
                date = date,
                isSelected = date == selectedDate,
                isToday = date == today,
                onClick = { onDateSelected(date) }
            )
        }
    }
}

@Composable
fun CalendarDay(
    date: LocalDate,
    isSelected: Boolean,
    isToday: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isSelected) Color(0xFFFFF0F0) else Color.Transparent
    val textColor = when {
        isSelected -> Color(0xFFDE496E)
        isToday -> Color(0xFF1E293B)
        else -> Color(0xFF64748B)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .width(50.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .background(backgroundColor)
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = date.dayOfMonth.toString(),
            fontSize = 18.sp,
            fontFamily = InterFontFamily(),
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold,
            color = textColor,
            textAlign = TextAlign.Center
        )

        Text(
            text = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
            fontSize = 14.sp,
            fontFamily = InterFontFamily(),
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = textColor,
            textAlign = TextAlign.Center
        )
        if (isSelected) {
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFDE496E))
            )
        }
    }
}