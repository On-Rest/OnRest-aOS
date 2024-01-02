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
