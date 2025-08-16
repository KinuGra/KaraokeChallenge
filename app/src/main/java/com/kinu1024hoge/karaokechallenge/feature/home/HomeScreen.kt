package com.kinu1024hoge.karaokechallenge.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
// ... 他のimport文 ...
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
// import androidx.compose.ui.res.painterResource // アイコンを使用しないためコメントアウトまたは削除
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// import com.kinu1024hoge.karaokechallenge.R // アイコンを使用しないためコメントアウトまたは削除

// UIの実際のコンテンツ部分
@Composable
fun KaraokeChallengeContent(onStartClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        // 大見出しセクション
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "カラオケチャレンジ",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp
                ),
                textAlign = TextAlign.Center
            )
        }

        // 中見出し・紹介文セクション
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "カラオケで盛り上がろう！",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "「カラオケチャレンジ」はカラオケを参加者全員一体となって盛り上げるゲームです",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        // ボタンセクション
        Button(
            onClick = onStartClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 24.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
        ) {
            Text(
                text = "チャレンジを始める！",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// スクリーン全体のエントリーポイントとなるComposable
@Composable
fun HomeScreen(
    // 必要であればViewModelやナビゲーションコントローラーなどを引数に取る
) {
    // ここでViewModelの準備や状態管理などを行う
    // 例: val viewModel: HomeViewModel = hiltViewModel()
    // 例: val navController = rememberNavController()

    KaraokeChallengeContent(
        onStartClick = {
            // "チャレンジを始める！"ボタンがクリックされたときの実際の処理
            // 例: navController.navigate("challenge_screen")
            println("スタートボタンがクリックされました！")
        }
    )
}

// @Preview 関数はトップレベルに配置する
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        // プレビュー用に、実際のロジックを持たないKaraokeChallengeContentを直接呼び出すか、
        // HomeScreenを呼び出す（ただし、HomeScreenが複雑な依存関係を持たない場合）
        KaraokeChallengeContent(onStartClick = {})
    }
}
