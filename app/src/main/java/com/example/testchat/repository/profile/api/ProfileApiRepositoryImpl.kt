package com.example.testchat.repository.profile.api

import android.util.Log
import com.example.testchat.retrofit.ApiService
import com.example.testchat.room.AppDatabase
import com.example.testchat.room.model.ProfileRoomData
import javax.inject.Inject
import com.example.testchat.room.mappers.toDbProfileData

class ProfileApiRepositoryImpl  @Inject constructor(
    private val apiService: ApiService
) : ProfileApiRepository {
    override suspend fun getProfileData(): ProfileRoomData? {
        return try {
            val response = apiService.getProfileData()
            if (response.isSuccessful) {
                Log.e("ProfileApi",response.body()?.profileApiData.toString())
                Log.e("ProfileApiToDb",response.body()?.profileApiData?.toDbProfileData().toString())
                response.body()?.profileApiData?.toDbProfileData()

            } else {
                Log.e("Response","Null")
                null
            }
        } catch (e: Exception) {
            Log.e("ResponseException",e.message.toString())
            null
        }
    }


}
