package com.chobo.onrest

import ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun execute(): ApiService = getBaseUrl().create(ApiService::class.java)

private fun getBaseUrl(): Retrofit = Retrofit.Builder()
    .baseUrl("http://46.250.250.34:5000/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()