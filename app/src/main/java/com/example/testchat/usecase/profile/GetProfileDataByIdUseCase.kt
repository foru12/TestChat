package com.example.testchat.usecase.profile

import com.example.testchat.repository.profile.room.ProfileRoomRepository
import com.example.testchat.room.model.ProfileRoomData

class GetProfileDataByIdUseCase(private val repository: ProfileRoomRepository) {
    suspend operator fun invoke(id: Int): ProfileRoomData? {
        return repository.getProfileDataById(id)
    }
}