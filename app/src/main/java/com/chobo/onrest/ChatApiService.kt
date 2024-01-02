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
