package com.kinu1024hoge.karaokechallenge.navigation

import android.R.attr.defaultValue
import android.R.attr.type
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kinu1024hoge.karaokechallenge.feature.challenge.ChallengeScreen
import com.kinu1024hoge.karaokechallenge.feature.home.HomeScreen
import com.kinu1024hoge.karaokechallenge.feature.result.ResultScreen
import com.kinu1024hoge.karaokechallenge.feature.test.TestPing

@Composable
fun AppNavGraph() {
    val nav = rememberNavController()

    NavHost(navController = nav, startDestination = Destinations.HOME) {

        composable(Destinations.TESTPING) { TestPing() }

        composable(Destinations.HOME) {
            HomeScreen(onStart = { nav.navigate(Destinations.CHALLENGE) })
        }

        composable(Destinations.CHALLENGE) {
            ChallengeScreen(
                onCompleted = { promptId, score ->
                    nav.navigate(Destinations.result(promptId, score))
                },
                onBack = { nav.popBackStack() }
            )
        }

        composable(
            route = Destinations.RESULT,
            arguments = listOf(
                navArgument("promptId") { type = NavType.IntType },
                navArgument("score")    { type = NavType.IntType; defaultValue = 0 },
            )
        ) { backStackEntry ->
            val promptId = backStackEntry.arguments?.getInt("promptId") ?: 0
            val score    = backStackEntry.arguments?.getInt("score") ?: 0

            ResultScreen(
                promptId = promptId,
                score    = score,
                onBackHome = {
                    nav.popBackStack(Destinations.HOME, inclusive = false)
                }
            )
        }
    }
}
