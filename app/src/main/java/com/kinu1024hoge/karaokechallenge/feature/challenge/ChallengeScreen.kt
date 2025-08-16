package com.kinu1024hoge.karaokechallenge.feature.challenge

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ChallengeScreen(
    onCompleted: () -> Unit,
    onBack: () -> Unit
) {
    Text("チャレンジ画面")
}
