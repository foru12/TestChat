package com.example.testchat.repository.profile.api

import com.example.testchat.retrofit.model.ResponseEditProfileData
import com.example.testchat.retrofit.model.RequestProfileData
import retrofit2.Response


interface EditProfileRepository {
    suspend fun editProfile(request: RequestProfileData): Response<ResponseEditProfileData>
}