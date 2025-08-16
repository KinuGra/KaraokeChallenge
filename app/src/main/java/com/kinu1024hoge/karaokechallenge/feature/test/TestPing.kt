package com.kinu1024hoge.karaokechallenge.feature.test

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TestPing() {
    val vm: TestPingViewModel = viewModel()
    val ui by vm.uiState.collectAsState()

    Column {
        Button(onClick = { vm.callPing() }) {
            Text("Call /ping")
        }

        when {
            ui.loading -> Text("Loading...")
            ui.error != null -> Text("Error: ${ui.error}")
            ui.result != null -> Text("Result: ${ui.result}")
            else -> Text("Press the button to test")
        }
    }
}