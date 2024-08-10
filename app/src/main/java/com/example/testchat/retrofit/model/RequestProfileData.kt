package com.example.testchat.retrofit.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@Parcelize
data class RequestProfileData(
 @SerialName("name"      )   var name      : String? = null,
 @SerialName("username"  )   var username  : String? = null,
 @SerialName("birthday"  )   var birthday  : String? = null,
 @SerialName("city"      )   var city      : String? = null,
 @SerialName("vk"        )   var vk        : String? = null,
 @SerialName("instagram" )   var instagram : String? = null,
 @SerialName("status"    )   var status    : String? = null,
 @SerialName("avatar"    )   var avatar    : RequestAvatar? = RequestAvatar()
): Parcelable










