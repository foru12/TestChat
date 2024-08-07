package com.example.testchat.room.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime


@Entity(tableName = "profile_data")
data class ProfileRoomData(
    @PrimaryKey val id: Int,
    val name: String,
    val username: String,
    val birthday: LocalDate,
    val city: String,
    val vk: String,
    val instagram: String,
    val status: String,
    val avatar: String,
    val last: LocalDateTime,
    val online: Boolean,
    val created: LocalDateTime,
    val phone: String,
    val completed_task: Int,
    @Embedded val avatars: AvatarsRoomData
)