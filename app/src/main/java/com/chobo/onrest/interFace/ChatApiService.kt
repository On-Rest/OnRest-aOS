package com.chobo.onrest.interFace

import com.chobo.onrest.dataclass.ChatRequestBody
import com.chobo.onrest.dataclass.ChatResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ChatApiService {
    @Headers("Content-Type: application/json",
        "Authorization: Bearer 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")
    @POST("v1/chat/completions")
    suspend fun getChatCompletion(@Body requestBody: ChatRequestBody): ChatResponse
}
