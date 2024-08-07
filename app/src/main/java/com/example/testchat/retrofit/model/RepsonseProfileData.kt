package com.example.testchat.retrofit.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepsonseProfileData (
    @SerialName("profile_data") var profileApiData : ProfileApiData? = ProfileApiData()

)