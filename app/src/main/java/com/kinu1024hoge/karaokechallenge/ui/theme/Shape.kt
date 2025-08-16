package com.kinu1024hoge.karaokechallenge.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

// ブランド統一のシェイプ（角丸スタイル）
val AppShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp), // ボタンなどでよく使われる
    large = RoundedCornerShape(20.dp),
    extraLarge = RoundedCornerShape(28.dp) // ダイアログや大きなカード
)
