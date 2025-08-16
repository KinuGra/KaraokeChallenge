package com.kinu1024hoge.karaokechallenge.navigation

object Destinations {
    const val HOME = "home"
    const val CHALLENGE = "challenge"
    const val TESTPING = "testPing"

    const val RESULT = "result/{promptId}/{score}"
    fun result(promptId: Int, score: Int) = "result/$promptId/$score"
}