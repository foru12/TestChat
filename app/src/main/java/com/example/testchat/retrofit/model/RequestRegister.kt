package com.example.testchat.retrofit.model

import kotlinx.serialization.Serializable


@Serializable
data class RequestRegister(
    val phone: String,
    val name: String,
    val username: String
)
