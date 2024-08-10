package com.example.testchat.retrofit.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class RequestAvatar(
    var filename : String? = null,
    var base_64   : String? = null
): Parcelable