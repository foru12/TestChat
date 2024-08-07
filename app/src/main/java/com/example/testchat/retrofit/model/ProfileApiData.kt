package com.example.testchat.retrofit.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileApiData (
    @SerialName("name"           ) var name          : String?  = null,
    @SerialName("username"       ) var username      : String?  = null,
    @SerialName("birthday"       ) var birthday      : String?  = null,
    @SerialName("city"           ) var city          : String?  = null,
    @SerialName("vk"             ) var vk            : String?  = null,
    @SerialName("instagram"      ) var instagram     : String?  = null,
    @SerialName("status"         ) var status        : String?  = null,
    @SerialName("avatar"         ) var avatar        : String?  = null,
    @SerialName("id"             ) var id            : Int?     = null,
    @SerialName("last"           ) var last          : String?  = null,
    @SerialName("online"         ) var online        : Boolean? = null,
    @SerialName("created"        ) var created       : String?  = null,
    @SerialName("phone"          ) var phone         : String?  = null,
    @SerialName("completed_task" ) var completedTask : Int?     = null,
    @SerialName("avatars"        ) var avatars       : AvatarsApiData? = AvatarsApiData()
)