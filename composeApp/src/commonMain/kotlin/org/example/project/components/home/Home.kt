package org.example.project.components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.avatar
import org.example.project.SharedViewModel
import org.example.project.theme.InterFontFamily
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun Home(){


    Row (
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Good Morning,",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = InterFontFamily(),
                color = Color(0xFF1E293B),
                letterSpacing = (0.3).sp
            )
            Text(
                text = "Shuri",
                fontWeight = FontWeight.Bold,
                fontFamily = InterFontFamily(),
                fontSize = 24.sp,
                color = Color(0xFF1E293B),
                letterSpacing = (0.3).sp
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(Res.drawable.avatar),
            contentDescription = "User Avatar",
            modifier = Modifier
                .size(55.dp)
        )
    }
}