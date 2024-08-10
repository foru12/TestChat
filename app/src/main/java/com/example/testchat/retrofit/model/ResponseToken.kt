package com.example.testchat.retrofit.model

import kotlinx.serialization.Serializable


@Serializable
data class ResponseToken(
    val refresh_token: String,
    val access_token: String,
    val user_id: Int? = null
)
