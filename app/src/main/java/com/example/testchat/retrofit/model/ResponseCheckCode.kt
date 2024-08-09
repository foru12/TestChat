package com.example.testchat.retrofit.model

import kotlinx.serialization.Serializable


@Serializable
data class ResponseCheckCode(
    val refresh_token: String,
    val access_token: String,
    val user_id: Int,
    val is_user_exists: Boolean
)