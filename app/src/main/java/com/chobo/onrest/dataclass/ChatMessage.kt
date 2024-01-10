package com.chobo.onrest.dataclass

data class ChatMessage(val role: String, val content: String)

data class ChatRequestBody(val model: String, val messages: List<ChatMessage>)

data class ChatResponse(val choices: List<ChatChoice>)

data class ChatChoice(val message: ChatMessage)