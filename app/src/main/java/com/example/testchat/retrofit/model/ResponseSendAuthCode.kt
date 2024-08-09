package com.example.testchat.retrofit.model

import kotlinx.serialization.Serializable

@Serializable
data class ResponseSendAuthCode(
    val is_success: Boolean
)