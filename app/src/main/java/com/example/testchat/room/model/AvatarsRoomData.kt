package com.example.testchat.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity


@Entity(tableName = "avatars")
data class AvatarsRoomData(
    @ColumnInfo(name = "avatar_url") val avatar: String,
    val bigAvatar: String,
    val miniAvatar: String
)
