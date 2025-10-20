package org.example.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(viewModel: SharedViewModel) {  // ← ПРИНИМАЕМ ViewModel ИЗВНЕ
    MaterialTheme {
        val navController = rememberNavController()

        Column(modifier = Modifier.fillMaxSize()) {
            AppNavigation(navController, viewModel)
        }
    }
}