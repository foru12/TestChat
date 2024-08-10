package com.example.testchat.usecase.profile

import com.example.testchat.repository.profile.room.ProfileRoomRepository
import com.example.testchat.room.model.ProfileRoomData
import javax.inject.Inject

class InsertProfileDataUseCase@Inject constructor (private val repository: ProfileRoomRepository) {
    suspend operator fun invoke(profileRoomData: ProfileRoomData) {
        repository.insertProfileData(profileRoomData)
    }
}