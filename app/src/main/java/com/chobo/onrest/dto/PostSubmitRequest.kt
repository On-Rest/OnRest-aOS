package com.chobo.onrest.dto

data class PostSubmitRequest(
    val doc : String,
    val subject : String,
    val clientId : String,
    val type: String,
    val emotion : Int
)