package com.kinu1024hoge.karaokechallenge.feature.result

import android.R.attr.onClick
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ResultScreen(
    promptId: Int,
    score: Int,
    onBackHome: () -> Unit
) {
    val message = when {
        score >= 90 -> "すごい！完璧に近い達成度！"
        score >= 70 -> "よくできました！あと少し！"
        score >= 50 -> "まあまあ！次はもっとがんばろう！"
        else -> "挑戦したことに意味がある！次回に期待！"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "結果発表",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Text(
            text = "お題ID: $promptId",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "スコア: $score 点",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp),
            textAlign = TextAlign.Center
        )

        Text(
            text = message,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
        )

        Button(onClick = onBackHome) {
            Text("タイトルに戻る")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    MaterialTheme {
        ResultScreen(
            promptId = 1,
            score = 85,
            onBackHome = {}
        )
    }
}