package com.example.testchat.retrofit.model

import kotlinx.serialization.Serializable


@Serializable
data class RequestSendAuthCode(
    val phone: String
)