package com.example.testchat.room.mappers

import com.example.testchat.retrofit.model.ProfileApiData
import com.example.testchat.room.model.AvatarsRoomData
import com.example.testchat.room.model.ProfileRoomData
import java.time.LocalDate
import java.time.LocalDateTime
import com.example.testchat.room.mappers.toDbProfileData
import com.example.testchat.room.mappers.toDbAvatarsData

fun ProfileApiData.toDbProfileData(): ProfileRoomData {
    return ProfileRoomData(
        id = this.id ?: 0,
        name = this.name,
        username = this.username,
        birthday = this.birthday,
        city = this.city,
        vk = this.vk,
        instagram = this.instagram,
        status = this.status,
        avatar = this.avatar,
        last = this.last,
        online = this.online,
        created = this.created,
        phone = this.phone,
        completed_task = this.completedTask,
        avatars = this.toDbAvatarsData()
    )
}

fun ProfileApiData.toDbAvatarsData(): AvatarsRoomData {
    return AvatarsRoomData(
        avatar = this.avatars?.avatar,
        bigAvatar = this.avatars?.bigAvatar,
        miniAvatar = this.avatars?.miniAvatar
    )
}

