package com.example.testchat.repository.profile.api

import com.example.testchat.retrofit.ApiService
import com.example.testchat.room.AppDatabase
import com.example.testchat.room.model.ProfileRoomData
import javax.inject.Inject
import com.example.testchat.room.mappers.toDbProfileData

class ProfileApiRepositoryImpl  @Inject constructor(
    private val apiService: ApiService,
    private val database: AppDatabase
) : ProfileApiRepository {
    override suspend fun getProfileData(): ProfileRoomData? {
        return try {
            val response = apiService.getProfileData()
            if (response.isSuccessful) {
                response.body()?.profileApiData?.toDbProfileData()

            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun saveProfileData(profile: ProfileRoomData) {
        database.profileDataDao().insertProfileData(profile)
    }
}
