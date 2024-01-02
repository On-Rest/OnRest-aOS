package com.chobo.onrest.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class RetrofitClass {
    val postService = Retrofit.Builder()
        .baseUrl("http://46.250.250.34:5000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(PostService::class.java)
}