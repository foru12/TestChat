package com.example.testchat.repository.profile

import android.util.Log
import com.example.testchat.Logger
import com.example.testchat.repository.profile.api.ProfileApiRepository
import com.example.testchat.repository.profile.room.ProfileRoomRepository
import com.example.testchat.room.model.ProfileRoomData
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val apiRepository: ProfileApiRepository,
    private val roomRepository: ProfileRoomRepository
) : ProfileRepository {

    override suspend fun fetchAndSaveProfileData(): ProfileRoomData? {
        val result = apiRepository.getProfileData()

        return if (result.isSuccess) {
            val profileData = result.getOrNull()
            Logger.e("ProfileData", profileData.toString())
            profileData?.let {
                roomRepository.insertProfileData(it)
            }
            profileData
        } else {
            Logger.e("ProfileDataError", result.exceptionOrNull()?.message ?: "Unknown error")
            null
        }
    }
}