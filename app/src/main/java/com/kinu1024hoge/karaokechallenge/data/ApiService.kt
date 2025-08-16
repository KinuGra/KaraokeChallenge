package com.kinu1024hoge.karaokechallenge.data

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

data class PingResponse(val message: String)
data class PromptDto(val id: Int, val content: String)

data class ScorePostBody(val promptId: Int, val score: Int)

data class ScoreStatsDto(
    val count: Int,
    val avg: Double?,   // データなしなら null
    val min: Int?,
    val max: Int?
)

data class ScorePostResponse(
    val ok: Boolean,
    val promptId: Int,
    val yourScore: Int,
    val stats: ScoreStatsDto
)

interface ApiService {
    @GET("ping")
    suspend fun ping(): PingResponse

    @GET("prompts/random")
    suspend fun getRandomPrompt(): PromptDto

    @POST("scores")
    suspend fun postScore(@Body body: ScorePostBody): ScorePostResponse
}