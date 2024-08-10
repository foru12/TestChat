package com.example.testchat.room.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "avatars")
data class AvatarsRoomData(
    @ColumnInfo(name = "avatar_url") val avatar: String?,
    val bigAvatar: String?,
    val miniAvatar: String?
): Parcelable
