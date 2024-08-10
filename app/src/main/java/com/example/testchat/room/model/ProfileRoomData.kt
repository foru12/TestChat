package com.example.testchat.room.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime

@Parcelize
@Entity(tableName = "profile_data")
data class ProfileRoomData(
    @PrimaryKey val id: Int,
    val name: String?,
    val username: String?,
    val birthday: String?,
    val city: String?,
    val vk: String?,
    val instagram: String?,
    val status: String?,
    val avatar: String?,
    val last: String?,
    val online: Boolean?,
    val created: String?,
    val phone: String?,
    val completed_task: Int?,
    @Embedded val avatars: AvatarsRoomData?
): Parcelable