package com.kinu1024hoge.karaokechallenge.feature.result

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kinu1024hoge.karaokechallenge.ui.theme.ResultStart
import com.kinu1024hoge.karaokechallenge.ui.theme.ResultEnd
import com.kinu1024hoge.karaokechallenge.ui.theme.KaraokeChallengeTheme

@Composable
fun ResultScreen(
    promptId: Int,
    score: Int,
    avg: Float?,   // 全国平均（null の場合はデータなし）
    count: Int,    // 集計件数（n）
    onBackHome: () -> Unit
) {
    // スコアのアニメーション表示
    val animatedScore by animateIntAsState(
        targetValue = score,
        animationSpec = tween(durationMillis = 800)
    )

    val message = when {
        score >= 90 -> "すごい！完璧に近い達成度！"
        score >= 70 -> "よくできました！あと少し！"
        score >= 50 -> "まあまあ！次はもっとがんばろう！"
        else -> "挑戦したことに意味がある！次回に期待！"
    }

    val avgText = avg?.let { String.format("%.1f", it) } ?: "--"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(ResultStart, ResultEnd)
                )
            )
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "結果発表",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                textAlign = TextAlign.Center
            )

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xCCFFFFFF)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally, // 中央揃え
                    verticalArrangement = Arrangement.Center
                ) {
//                    Text(
//                        text = "お題ID: $promptId",
//                        style = MaterialTheme.typography.bodyLarge,
//                        textAlign = TextAlign.Center
//                    )

                    Text(
                        text = "$animatedScore 点",
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 48.sp,
                            color = Color(0xFFf57c00)
                        ),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "全国平均: $avgText 点（n=$count）",
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = message,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Medium),
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onBackHome,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFf57c00),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "タイトルに戻る",
                    color = Color.White, // 文字色を白に
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    KaraokeChallengeTheme {
        ResultScreen(
            promptId = 1,
            score = 85,
            avg = 76.5f,
            count = 12,
            onBackHome = {}
        )
    }
}