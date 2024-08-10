package com.example.testchat.retrofit.model

import kotlinx.serialization.Serializable


@Serializable
data class EditAvatars(
   var avatar     : String? = null,
   var bigAvatar  : String? = null,
   var miniAvatar : String? = null

)
