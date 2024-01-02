package com.chobo.onrest

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PostService {
    @POST("/api/pins")
    fun submitPost(@Body postSubmitRequest: PostSubmitRequest): Call<PostSubmitRequest>
}