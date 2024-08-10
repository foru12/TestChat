package com.example.testchat.ui.profile.viewmodel

import com.example.testchat.retrofit.model.RequestProfileData
import com.example.testchat.retrofit.model.ResponseEditProfileData

sealed class EditProfileState {

    object Idle : EditProfileState()

    object Loading : EditProfileState()

    data class Success(val data: RequestProfileData?) : EditProfileState()

    data class Error(val message: String) : EditProfileState()
}