package com.kinu1024hoge.karaokechallenge.data

import retrofit2.http.GET

data class PingResponse(val message: String)

interface ApiService {
    @GET("ping")
    suspend fun ping(): PingResponse
}