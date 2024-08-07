package com.example.testchat.usecase.profile

import com.example.testchat.repository.profile.room.ProfileRoomRepository
import com.example.testchat.room.model.ProfileRoomData

class DeleteProfileDataUseCase(private val repository: ProfileRoomRepository) {
    suspend operator fun invoke(profileRoomData: ProfileRoomData) {
        repository.deleteProfileData(profileRoomData)
    }
}