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
