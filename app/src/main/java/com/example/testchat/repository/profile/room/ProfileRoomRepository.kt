package com.example.testchat.repository.profile.room

import com.example.testchat.room.model.ProfileRoomData

interface ProfileRoomRepository {
    suspend fun getProfileDataById(id: Int): ProfileRoomData?
    suspend fun insertProfileData(profileRoomData: ProfileRoomData)
    suspend fun deleteProfileData(profileRoomData: ProfileRoomData)
}