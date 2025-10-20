package org.example.project.components.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.theme.InterFontFamily

@Composable
fun TimeSelect(
    startHour: Int?,
    startMinute: Int?,
    endHour: Int?,
    endMinute: Int?,
    onStartHourChanged: (Int) -> Unit,
    onStartMinuteChanged: (Int) -> Unit,
    onEndHourChanged: (Int) -> Unit,
    onEndMinuteChanged: (Int) -> Unit
) {
    Column {
        Text(
            text = "Select time",
            fontWeight = FontWeight.Medium,
            fontFamily = InterFontFamily(),
            fontSize = 16.sp,
            letterSpacing = 0.3.sp
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
                .background(Color(0xFFF1F5F9), RoundedCornerShape(10.dp))
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    text = "From",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray,
                    fontFamily = InterFontFamily(),
                    modifier = Modifier.padding(start = 20.dp)
                )
                Spacer(modifier = Modifier.size(4.dp))
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    TimeInputField(
                        placeholder = "HH",
                        value = startHour,
                        onValueChange = onStartHourChanged,
                        modifier = Modifier.weight(1f),
                        maxValue = 23
                    )
                    Text(
                        text = ":",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                    TimeInputField(
                        placeholder = "MM",
                        value = startMinute,
                        onValueChange = onStartMinuteChanged,
                        modifier = Modifier.weight(1f),
                        maxValue = 59
                    )
                }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.size(20.dp))
                Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "ArrowRight",
                    modifier = Modifier.size(32.dp)
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    text = "To",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray,
                    fontFamily = InterFontFamily(),
                    modifier = Modifier.padding(start = 20.dp)
                )
                Spacer(modifier = Modifier.size(4.dp))
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    TimeInputField(
                        placeholder = "HH",
                        value = endHour,
                        onValueChange = onEndHourChanged,
                        modifier = Modifier.weight(1f),
                        maxValue = 23
                    )
                    Text(
                        text = ":",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                    TimeInputField(
                        placeholder = "MM",
                        value = endMinute,
                        onValueChange = onEndMinuteChanged,
                        modifier = Modifier.weight(1f),
                        maxValue = 59
                    )
                }
            }
        }
    }
}

@Composable
fun TimeInputField(
    placeholder: String,
    value: Int?,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    maxValue: Int = 23
) {
    var text by remember { mutableStateOf(value?.toString() ?: "") }

    OutlinedTextField(
        value = text,
        onValueChange = { newText ->
            text = newText.take(2).filter { it.isDigit() }
            text.toIntOrNull()?.takeIf { it in 0..maxValue }?.let(onValueChange)
        },
        placeholder = {
            Text(
                text = placeholder,
                color = Color.Gray
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        textStyle = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = InterFontFamily(),
            letterSpacing = 0.3.sp
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = modifier
    )
}