package com.example.testchat.retrofit.model

import kotlinx.serialization.Serializable


@Serializable
data class RequestCheckCode (
    val phone: String,
    val code: String
)