package com.chobo.onrest

data class PostSubmitRequest(
    val doc : String,
    val subject : String,
    val clientId : String,
    val type: String = "board",
    val emotion : Int
)