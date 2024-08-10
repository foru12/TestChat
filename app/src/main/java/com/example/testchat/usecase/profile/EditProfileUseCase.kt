package com.example.testchat.usecase.profile

import com.example.testchat.repository.profile.api.EditProfileRepository
import com.example.testchat.retrofit.model.ResponseEditProfileData
import com.example.testchat.retrofit.model.RequestProfileData
import retrofit2.Response
import javax.inject.Inject

class EditProfileUseCase @Inject constructor(
    private val editProfileRepository: EditProfileRepository
) {
    suspend fun execute(request: RequestProfileData): Response<ResponseEditProfileData> {
        return editProfileRepository.editProfile(request)
    }
}