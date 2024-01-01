package com.chobo.onrest

import ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
class ApiServiceImpl(){
    fun execute(){
        var retrofit = getBaseUrl()

        var apiService: ApiService = retrofit.create(ApiService::class.java)
        var postService: PostService = retrofit.create(PostService::class.java)

        postService.submitPost(PostSubmitRequest(
            doc = "인성파탄",
            clientId = "siasiaman",
            type = "board",
            subject = "당신은 인성파탄자입니다.",
            emotion = 1
        ))
    }

    fun submitBoard(doc : String, subject : String, clientId : String, type: String, emotion : Int){
        var retrofit = getBaseUrl()

        var apiService: ApiService = retrofit.create(ApiService::class.java)
        var postService: PostService = retrofit.create(PostService::class.java)

        postService.submitPost(PostSubmitRequest(
            doc = doc,
            clientId = clientId,
            type = type,
            subject = subject,
            emotion = emotion
        ))
    }
    private fun getBaseUrl(): Retrofit = Retrofit.Builder()
        .baseUrl("http://46.250.250.34:5000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}