package com.kinu1024hoge.karaokechallenge.data

class PingRepository(
    private  val api: ApiService = ApiClient.api
) {
    suspend fun ping(): Result<PingResponse> = runCatching {
        api.ping()
    }
}