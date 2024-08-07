package com.example.testchat.ui.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testchat.room.model.ProfileRoomData
import com.example.testchat.usecase.profile.DeleteProfileDataUseCase
import com.example.testchat.usecase.profile.GetProfileDataByIdUseCase
import com.example.testchat.usecase.profile.InsertProfileDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileDataByIdUseCase: GetProfileDataByIdUseCase,
    private val insertProfileDataUseCase: InsertProfileDataUseCase,
    private val deleteProfileDataUseCase: DeleteProfileDataUseCase
) : ViewModel() {

    fun getProfileDataById(id: Int) {
        viewModelScope.launch {
            val profileData = getProfileDataByIdUseCase(id)

        }
    }

    fun insertProfileData(profileRoomData: ProfileRoomData) {
        viewModelScope.launch {
            insertProfileDataUseCase(profileRoomData)
        }
    }

    fun deleteProfileData(profileRoomData: ProfileRoomData) {
        viewModelScope.launch {
            deleteProfileDataUseCase(profileRoomData)
        }
    }
}