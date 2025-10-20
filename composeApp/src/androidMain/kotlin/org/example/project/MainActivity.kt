package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    private val viewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.initializeWithContext(this)

        setContent {
            App(viewModel)  // ← ПЕРЕДАЁМ ViewModel В APP
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    val viewModel = SharedViewModel()  // ← ДЛЯ ПРЕВЬЮ
    App(viewModel)
}