package com.kinu1024hoge.karaokechallenge.feature.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kinu1024hoge.karaokechallenge.feature.home.ui.HomeScreenStyles
import com.kinu1024hoge.karaokechallenge.ui.theme.KaraokeChallengeTheme

@Composable
fun HomeScreen(onStart: () -> Unit) {
    KaraokeChallengeContent(onStart)
}

@Composable
fun KaraokeChallengeContent(onStart: () -> Unit) {
    var showDescription by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        // 大見出し
        Text(
            text = "カラオケチャレンジ",
            style = HomeScreenStyles.title()
        )

        // 中見出し・紹介文（説明非表示のときだけ出す）
        AnimatedVisibility(visible = !showDescription) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "カラオケで盛り上がろう！",
                    style = HomeScreenStyles.subTitle(),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "「カラオケチャレンジ」はカラオケを参加者全員一体となって盛り上げるゲームです",
                    style = HomeScreenStyles.shortDescription(),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                )
            }
        }

        // トグルボタン
        OutlinedButton(
            onClick = { showDescription = !showDescription },
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(0.6f),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = if (showDescription) "説明を閉じる" else "説明を見る",
                style = HomeScreenStyles.toggleButtonText()
            )
        }

        // 詳細説明（折り畳みアニメーション付き）
        AnimatedVisibility(
            visible = showDescription,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            Text(
                text = """
このアプリはカラオケを盛大に盛り上げる目的で開発されました。

ルール説明
誰かが歌うときに、「チャレンジを始める」ボタンを押しましょう。
ここからが本番です！
出されたお題を確認します。

歌い終わった後

(辛口でも甘口でも)お題が達成できているか点数で評価しましょう！

これで終わりです！
                """.trimIndent(),
                style = HomeScreenStyles.longDescription(),
                modifier = Modifier.padding(12.dp)
            )
        }

        // メインボタン
        Button(
            onClick = onStart,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 24.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            shape = MaterialTheme.shapes.large,
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
        ) {
            Text(
                text = "チャレンジを始める！",
                style = HomeScreenStyles.startButtonText()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    KaraokeChallengeTheme {
        KaraokeChallengeContent(onStart = {})
    }
}