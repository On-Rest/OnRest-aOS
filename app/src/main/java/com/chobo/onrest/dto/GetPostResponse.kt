package com.chobo.onrest.dto

import com.google.gson.annotations.SerializedName

data class GetPostResponse(
    val _id : Id,
    val data : Data,
    val detail : String,
    val client_id : String,
    val like_count : Int,
    val emotion : ArrayList<Int>,
    val subject : String
)
data class Id(
    @SerializedName("\$oid")
    val oid : String
)
data class Data(
    @SerializedName("\$date")
    val date : String
)


/*
"_id": {
            "$oid": "6593d5f139bc0fa947fed285"
        },
        "date": {
            "$date": "2024-01-02T03:58:36.351Z"
        },
        "detail": "테스트",
        "client_id": "test",
        "like_count": 0,
        "emotion": "4",
        "subject": "테스트"
    },
 */