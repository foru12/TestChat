package com.example.testchat.retrofit.model

import kotlinx.serialization.Serializable


@Serializable
data class ResponseEditProfileData(
    var avatars : EditAvatars? = EditAvatars()
)
