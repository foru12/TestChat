package com.example.testchat.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testchat.room.model.ProfileRoomData


@Dao
interface ProfileDataDao {
    @Query("SELECT * FROM profile_data WHERE id = :id")
    suspend fun getProfileDataById(id: Int): ProfileRoomData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfileData(profileRoomData: ProfileRoomData)

    @Delete
    suspend fun deleteProfileData(profileRoomData: ProfileRoomData)
}