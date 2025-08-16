package com.kinu1024hoge.karaokechallenge.feature.home

import android.R.attr.onClick
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen() {
    Column() {
        Text("カラオケチャレンジ")
        Button(onClick = {}) {
            Text("始める")
        }
    }
}