package com.example.testchat.usecase.splash

import com.example.testchat.repository.profile.api.ProfileApiRepository
import javax.inject.Inject

class LoadProfileDataUseCase @Inject constructor(
    private val profileRepository: ProfileApiRepository
) {
    suspend fun execute() {
        val profile = profileRepository.getProfileData()
        profile?.let {
            profileRepository.saveProfileData(it)
        }
    }
}