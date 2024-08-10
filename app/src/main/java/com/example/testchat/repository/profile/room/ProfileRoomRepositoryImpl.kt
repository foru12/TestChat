package com.example.testchat.repository.profile.room

import com.example.testchat.room.ProfileDataDao
import com.example.testchat.room.model.ProfileRoomData
import javax.inject.Inject

class ProfileRoomRepositoryImpl @Inject constructor(
    private val profileDataDao: ProfileDataDao
) : ProfileRoomRepository {

    override suspend fun getProfileDataById(id: Int): ProfileRoomData? {
        return profileDataDao.getProfileDataById(id)
    }

    override suspend fun insertProfileData(profileRoomData: ProfileRoomData) {
        profileDataDao.insertProfileData(profileRoomData)
    }

    override suspend fun deleteProfileData(profileRoomData: ProfileRoomData) {
        profileDataDao.deleteProfileData(profileRoomData)
    }

    override suspend fun updateProfileData(id: Int, name: String, birthday: String, city: String) {
        profileDataDao.updateProfileData(id,name,birthday,city)
    }
}