package com.kinu1024hoge.karaokechallenge.feature.challenge

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kinu1024hoge.karaokechallenge.data.ApiClient
import com.kinu1024hoge.karaokechallenge.data.ScorePostBody
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChallengeScreen(
    onCompleted: (promptId: Int, score: Int, avg: Float?, count: Int) -> Unit,
    onBack: () -> Unit
) {
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }
    var submitting by remember { mutableStateOf(false) }
    var promptId by remember { mutableStateOf<Int?>(null) }
    var currentChallenge by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        runCatching {
            loading = true
            error = null
            ApiClient.api.getRandomPrompt()
        }.onSuccess { dto ->
            promptId = dto.id
            currentChallenge = dto.content
        }.onFailure { e ->
            error = e.message ?: "ネットワークエラー"
        }
        loading = false
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("チャレンジ") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "戻る"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "お題",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            when {
                loading -> Text("読み込み中…")
                error != null -> {
                    Text(
                        text = "エラー: $error",
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(12.dp))
                    Button(
                        enabled = !submitting,
                        onClick = {
                            scope.launch {
                                runCatching {
                                    loading = true
                                    error = null
                                    ApiClient.api.getRandomPrompt()
                                }.onSuccess { dto ->
                                    promptId = dto.id
                                    currentChallenge = dto.content
                                }.onFailure { e ->
                                    error = e.message ?: "ネットワークエラー"
                                }
                                loading = false
                            }
                        }
                    ) { Text("再読み込み") }
                }
                else -> {
                    Text(
                        text = currentChallenge,
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .weight(1f)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(text = "歌う人はお題に取り組もう")
                    Text(text = "他のみんなは歌う人のお題の取り組みを評価")

                    Spacer(modifier = Modifier.weight(0.2f))

                    InputScoringPopup(
                        onScoreSelected = { score ->
                            val id = promptId ?: return@InputScoringPopup
                            if (submitting) return@InputScoringPopup
                            submitting = true
                            scope.launch {
                                try {
                                    val resp = ApiClient.api.postScore(ScorePostBody(id, score))
                                    val avgF: Float? = resp.stats.avg?.toFloat()
                                    val count = resp.stats.count
                                    onCompleted(id, score, avgF, count)
                                } catch (e: Exception) {
                                    // TODO: スナックバー等で通知
                                } finally {
                                    submitting = false
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChallengeScreenPreview() {
    MaterialTheme {
        ChallengeScreen(
            onCompleted = { _, _, _, _ -> },
            onBack = {}
        )
    }
}

