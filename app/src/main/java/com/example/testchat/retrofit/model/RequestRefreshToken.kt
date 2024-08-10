package com.example.testchat.retrofit.model

import kotlinx.serialization.Serializable


@Serializable
data class RequestRefreshToken(
    val refresh_token: String
)
