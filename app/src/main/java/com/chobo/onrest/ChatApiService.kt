package com.chobo.onrest

import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ChatApiService {
    @Headers("Content-Type: application/json",
<<<<<<< HEAD
        "Authorization: Bearer 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")
=======
        "Authorization: Bearer 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")
>>>>>>> 00c1702b20f74e9de36c0d476a2d729a44072c58
    @POST("v1/chat/completions")
    suspend fun getChatCompletion(@Body requestBody: ChatRequestBody): ChatResponse
}
