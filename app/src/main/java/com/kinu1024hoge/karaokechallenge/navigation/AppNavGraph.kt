package com.kinu1024hoge.karaokechallenge.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kinu1024hoge.karaokechallenge.feature.challenge.ChallengeScreen
import com.kinu1024hoge.karaokechallenge.feature.home.HomeScreen
import com.kinu1024hoge.karaokechallenge.feature.test.TestPing

@Composable
fun AppNavGraph() {
    // どの画面を表示しているか、どの画面に遷移するかを管理
    val nav = rememberNavController()

    NavHost(navController = nav, startDestination = Destinations.HOME) {
        composable(Destinations.TESTPING) {
            // node.jsにテスト接続するための画面
            TestPing()
        }
        composable(Destinations.HOME) {
            HomeScreen(onStart = { nav.navigate(Destinations.CHALLENGE) })
        }
        composable(Destinations.CHALLENGE) {
            ChallengeScreen(
                onCompleted = {},
                onBack = { nav.popBackStack() }
            )
        }
    }
}