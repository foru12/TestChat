package com.example.testchat.retrofit.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseRegister(
    @SerialName("refresh_token") val refreshToken: String,
    @SerialName("access_token") val accessToken: String,
    @SerialName("user_id") val userId: Int
)
