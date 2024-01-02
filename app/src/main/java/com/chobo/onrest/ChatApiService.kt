package com.chobo.onrest

import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ChatApiService {
    @Headers("Content-Type: application/json",
        "Authorization: Bearer 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")

    @POST("v1/chat/completions")
    suspend fun getChatCompletion(@Body requestBody: ChatRequestBody): ChatResponse
}
