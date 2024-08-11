package com.example.testchat.usecase.splash

import com.example.testchat.repository.profile.ProfileRepository
import com.example.testchat.room.model.ProfileRoomData
import javax.inject.Inject

class LoadProfileDataUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend fun execute(): ProfileRoomData? {
        return profileRepository.fetchAndSaveProfileData()
    }
}