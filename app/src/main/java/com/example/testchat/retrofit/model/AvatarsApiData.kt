package com.example.testchat.retrofit.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AvatarsApiData(
    @SerialName("avatar"     ) var avatar     : String? = null,
    @SerialName("bigAvatar"  ) var bigAvatar  : String? = null,
    @SerialName("miniAvatar" ) var miniAvatar : String? = null
)
