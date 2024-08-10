package com.example.testchat.repository.profile

import com.example.testchat.room.model.ProfileRoomData

interface ProfileRepository {
    suspend fun fetchAndSaveProfileData(): ProfileRoomData?
}