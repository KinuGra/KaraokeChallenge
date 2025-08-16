package com.kinu1024hoge.karaokechallenge.feature.challenge

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun InputScoringPopup(
    showPopup: MutableState<Boolean>,
    onScoreSelected: (Int) -> Unit,
    onDismissRequest: () -> Unit
) {
    if (showPopup.value) {
        Dialog(
            onDismissRequest = onDismissRequest,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = MaterialTheme.shapes.medium,
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "お題達成度を選択してください",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp) // ボタン間にスペース
                    ) {
                        Button(onClick = {
                            onScoreSelected(90)
                            showPopup.value = false
                        }) {
                            Text("お題達成！（90点くらい）")
                        }
                        Button(onClick = {
                            onScoreSelected(70)
                            showPopup.value = false
                        }) {
                            Text("ほぼ達成！（70点くらい）")
                        }
                        Button(onClick = {
                            onScoreSelected(50)
                            showPopup.value = false
                        }) {
                            Text("半分達成！（50点くらい）")
                        }
                        Button(onClick = {
                            onScoreSelected(10)
                            showPopup.value = false
                        }) {
                            Text("全然ダメ！（10点くらい）")
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, name = "InputScoringPopup Dialog Preview")
@Composable
fun InputScoringPopupDialogPreview() {
    MaterialTheme {
        val show = remember { mutableStateOf(true) }
        InputScoringPopup(
            showPopup = show,
            onScoreSelected = {},
            // ポップアップ外側をクリックしたとき
            onDismissRequest = { show.value = false }
        )
    }
}

@Composable
fun InputScoringPopup(
    onScoreSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val showPopupState = remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { showPopupState.value = true }) {
            Text("お題達成度入力")
        }
    }

    InputScoringPopup(
        showPopup = showPopupState,
        onScoreSelected = onScoreSelected,
        onDismissRequest = { showPopupState.value = false }
    )
}

@Preview(showBackground = true, name = "ChallengeScoringComponent Preview")
@Composable
fun ChallengeScoringComponentPreview() {
    MaterialTheme {
        InputScoringPopup(
            onScoreSelected = { score ->
                println("Score selected in preview: $score")
            }
        )
    }
}
