package com.kinu1024hoge.karaokechallenge.feature.result

import android.R.attr.onClick
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ResultScreen(
    score: Int,
    onBackHome: () -> Unit
) {
    Column(

    ) {
        Button(onClick = onBackHome) {
            Text("タイトルに戻る")
        }
    }
}