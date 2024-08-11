package com.example.testchat.repository.profile.api

import com.example.testchat.Logger
import com.example.testchat.retrofit.ApiService
import com.example.testchat.room.mappers.toDbProfileData
import com.example.testchat.room.model.ProfileRoomData
import javax.inject.Inject

class ProfileApiRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ProfileApiRepository {
    override suspend fun getProfileData(): Result<ProfileRoomData?> {
        return try {
            val response = apiService.getProfileData()
            if (response.isSuccessful) {
                val profileData = response.body()?.profileApiData?.toDbProfileData()
                Logger.e("ProfileApi", profileData.toString())
                Result.success(profileData)
            } else {
                Logger.e("Response", "Null")
                Result.failure(Exception("API call failed with response: ${response.code()}"))
            }
        } catch (e: Exception) {
            Logger.e("ResponseException", e.message.toString())
            Result.failure(e)
        }
    }
}
