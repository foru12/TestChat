package com.example.testchat.repository.profile.api

import com.example.testchat.room.model.ProfileRoomData

interface ProfileApiRepository {
    suspend fun getProfileData(): ProfileRoomData?
}