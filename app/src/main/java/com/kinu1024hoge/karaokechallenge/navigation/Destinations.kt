package com.kinu1024hoge.karaokechallenge.navigation

object Destinations {
    const val HOME = "home"
    const val CHALLENGE = "challenge"
    const val TESTPING = "testPing"

    const val RESULT = "result/{promptId}/{score}/{avg}/{count}"

    fun resultWithStats(promptId: Int, score: Int, avg: Float?, count: Int): String {
        val safeAvg = avg ?: -1f
        return "result/$promptId/$score/$safeAvg/$count"
    }
}