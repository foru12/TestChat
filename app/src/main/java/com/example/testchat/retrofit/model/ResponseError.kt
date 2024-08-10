package com.example.testchat.retrofit.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class ResponseError(
    val detail: JsonElement,  
    val body: String
)