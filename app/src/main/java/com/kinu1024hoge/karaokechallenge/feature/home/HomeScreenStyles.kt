package com.kinu1024hoge.karaokechallenge.feature.home.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object HomeScreenStyles {

    @Composable
    fun title(): TextStyle = MaterialTheme.typography.headlineLarge.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        color = MaterialTheme.colorScheme.onBackground
    )

    @Composable
    fun subTitle(): TextStyle = MaterialTheme.typography.titleLarge.copy(
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        color = MaterialTheme.colorScheme.onBackground
    )

    @Composable
    fun shortDescription(): TextStyle = MaterialTheme.typography.bodyLarge.copy(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        color = MaterialTheme.colorScheme.onBackground
    )

    @Composable
    fun longDescription(): TextStyle = MaterialTheme.typography.bodyLarge.copy(
        fontSize = 18.sp,
        lineHeight = 26.sp,
        color = MaterialTheme.colorScheme.onBackground
    )

    @Composable
    fun toggleButtonText(): TextStyle = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        color = MaterialTheme.colorScheme.primary
    )

    @Composable
    fun startButtonText(): TextStyle = MaterialTheme.typography.titleMedium.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = MaterialTheme.colorScheme.onPrimary
    )
}
