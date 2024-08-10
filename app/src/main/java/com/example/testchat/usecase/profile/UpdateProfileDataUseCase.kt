package com.example.testchat.usecase.profile

import com.example.testchat.repository.profile.room.ProfileRoomRepository
import com.example.testchat.room.model.ProfileRoomData
import javax.inject.Inject

class UpdateProfileDataUseCase @Inject constructor (private val repository: ProfileRoomRepository) {
    suspend operator fun invoke(id: Int, name: String, birthday: String, city: String) {
        repository.updateProfileData(id,name,birthday,city)
    }
}