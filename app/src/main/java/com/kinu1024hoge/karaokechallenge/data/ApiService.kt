package com.kinu1024hoge.karaokechallenge.data

import retrofit2.http.GET

data class PingResponse(val message: String)
data class PromptDto(val id: Int, val content: String)

interface ApiService {
    @GET("ping")
    suspend fun ping(): PingResponse

    @GET("prompts/random")
    suspend fun getRandomPrompt(): PromptDto
}