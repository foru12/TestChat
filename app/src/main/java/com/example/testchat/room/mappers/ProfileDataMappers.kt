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
        name = this.name.orEmpty(),
        username = this.username.orEmpty(),
        birthday = LocalDate.parse(this.birthday.orEmpty()),
        city = this.city.orEmpty(),
        vk = this.vk.orEmpty(),
        instagram = this.instagram.orEmpty(),
        status = this.status.orEmpty(),
        avatar = this.avatar.orEmpty(),
        last = LocalDateTime.parse(this.last.orEmpty()),
        online = this.online ?: false,
        created = LocalDateTime.parse(this.created.orEmpty()),
        phone = this.phone.orEmpty(),
        completed_task = this.completedTask ?: 0,
        avatars = this.toDbAvatarsData() ?: AvatarsRoomData("", "", "")
    )
}

fun ProfileApiData.toDbAvatarsData(): AvatarsRoomData {
    return AvatarsRoomData(
        avatar = this.avatars?.avatar.orEmpty(),
        bigAvatar = this.avatars?.bigAvatar.orEmpty(),
        miniAvatar = this.avatars?.miniAvatar.orEmpty()
    )
}


