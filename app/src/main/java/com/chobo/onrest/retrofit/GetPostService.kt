package com.chobo.onrest.retrofit

import com.chobo.onrest.dto.GetPostResponse
import retrofit2.Call
import retrofit2.http.GET

interface GetPostService {
    @GET("api/pins")
    fun getPost() : Call<GetPostResponse>
}