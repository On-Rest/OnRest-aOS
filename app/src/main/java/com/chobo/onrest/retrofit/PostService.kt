package com.chobo.onrest.retrofit

import com.chobo.onrest.dto.GetPostResponse
import com.chobo.onrest.dto.PostSubmitRequest
import com.chobo.onrest.dto.PostSubmitResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PostService {
    @POST("api/pins")
    fun submitPost(@Body postSubmitRequest: PostSubmitRequest): Call<PostSubmitResponse>

    @GET("api/pins")
    fun getPost() : Call<GetPostResponse>
}