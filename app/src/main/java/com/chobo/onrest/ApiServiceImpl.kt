package com.chobo.onrest

import ApiService
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
class ApiServiceImpl {
    fun submitBoard(doc : String, subject : String, clientId : String, emotion : Int): Call<PostSubmitResponse>{
        var retrofit = getBaseUrl()
        var postService: PostService = retrofit.create(PostService::class.java)

        return postService.submitPost(PostSubmitRequest(
            doc = doc,
            subject = subject,
            clientId = clientId,
            emotion = emotion
        ))
    }
    private fun getBaseUrl(): Retrofit = Retrofit.Builder()
        .baseUrl("http://46.250.250.34:5000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}