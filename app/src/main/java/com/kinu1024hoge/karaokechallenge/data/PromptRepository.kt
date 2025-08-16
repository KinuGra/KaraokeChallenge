package com.kinu1024hoge.karaokechallenge.data

class PromptRepository {
    suspend fun fetchRandom(): PromptDto = ApiClient.api.getRandomPrompt()
}