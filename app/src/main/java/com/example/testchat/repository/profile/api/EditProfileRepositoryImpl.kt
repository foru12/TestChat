package com.example.testchat.repository.profile.api

import com.example.testchat.retrofit.ApiService
import com.example.testchat.retrofit.model.ResponseEditProfileData
import com.example.testchat.retrofit.model.RequestProfileData
import retrofit2.Response
import javax.inject.Inject

class EditProfileRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    EditProfileRepository {
    override suspend fun editProfile(request: RequestProfileData): Response<ResponseEditProfileData> {
        return apiService.editProfileData(request)
    }

}