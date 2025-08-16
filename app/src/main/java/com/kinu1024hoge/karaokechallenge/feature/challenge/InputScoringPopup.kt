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
    on70PointsClick: () -> Unit,
    on80PointsClick: () -> Unit,
    on90PointsClick: () -> Unit,
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
                    modifier = Modifier
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "採点結果を選択してください",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = on70PointsClick) { Text("70点") }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = on80PointsClick) { Text("80点") }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = on90PointsClick) { Text("90点") }
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
            on70PointsClick = { println("Preview: 70 points") },
            on80PointsClick = { println("Preview: 80 points") },
            on90PointsClick = { println("Preview: 90 points") },
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
        Button(
            onClick = { showPopupState.value = true }
        ) {
            Text("採点入力をする")
        }
    }

    InputScoringPopup(
        showPopup = showPopupState,
        on70PointsClick = {
            onScoreSelected(70)
            showPopupState.value = false
        },
        on80PointsClick = {
            onScoreSelected(80)
            showPopupState.value = false
        },
        on90PointsClick = {
            onScoreSelected(90)
            showPopupState.value = false
        },
        onDismissRequest = {
            showPopupState.value = false
        }
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
