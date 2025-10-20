package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        val viewModel: SharedViewModel = viewModel()

        val notifyActive by viewModel.notifyActive.collectAsState()
        val message by viewModel.message.collectAsState()
        val notificationTitle by viewModel.notificationTitle.collectAsState()

        AnimatedVisibility(
            visible = notifyActive,
            enter = fadeIn() + slideInVertically { -40 },
            exit = fadeOut() + slideOutVertically { -40 },
            modifier = Modifier.zIndex(2f)
        ) {
            TimedNotification(title = notificationTitle, message = message)
        }

        Column {
            AppNavigation(navController, viewModel)
        }
    }
}