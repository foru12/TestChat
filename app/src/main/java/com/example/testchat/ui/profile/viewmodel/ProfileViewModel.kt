package com.example.testchat.ui.profile.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testchat.Logger
import com.example.testchat.retrofit.model.RequestAvatar
import com.example.testchat.room.model.AvatarsRoomData
import com.example.testchat.room.model.ProfileRoomData
import com.example.testchat.usecase.auth.GetTokenUseCase
import com.example.testchat.usecase.profile.GetProfileDataByIdUseCase
import com.example.testchat.usecase.profile.InsertProfileDataUseCase
import com.example.testchat.usecase.profile.UpdateProfileDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileDataByIdUseCase: GetProfileDataByIdUseCase,
    private val insertProfileDataUseCase: InsertProfileDataUseCase,
    private val getTokenUseCase: GetTokenUseCase,

    ) : ViewModel() {

    private val _profileData = MutableLiveData<ProfileRoomData?>()
    val profileData: LiveData<ProfileRoomData?> = _profileData


    fun getProfileDataById() {
        viewModelScope.launch {
            val id = getTokenUseCase.invoke()?.userId
            val data = id?.let { getProfileDataByIdUseCase(it) }
            _profileData.postValue(data)
        }
    }

    fun insertProfileData(
        name: String?,
        username: String?,
        birthday: String?,
        city: String?,
        vk: String?,
        instagram: String?,
        status: String?,
        avatar: String?,
        last: String?,
        online: Boolean?,
        created: String?,
        phone: String?,
    ){
        viewModelScope.launch {
            val profileRoomData = ProfileRoomData(
                getTokenUseCase.invoke()?.userId ?: 0,
                name = name,
                username = username,
                birthday = birthday,
                city = city,
                vk = vk,
                instagram = instagram,
                status = status,
                avatar = avatar,
                last = last,
                online = online,
                created = created,
                phone = phone,
                completed_task = 0,
                avatars = null


            )
            insertProfileDataUseCase.invoke(profileRoomData)
        }
    }




    fun getZodiacSign(dateString: String): String {
        val dateOfBirth: LocalDate = try {
            LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE)
        } catch (e: Exception) {
            Logger.e("Data Locale", e.message.toString())
            return ""
        }

        return when {
            (dateOfBirth.isAfter(LocalDate.of(dateOfBirth.year, 3, 20)) && dateOfBirth.isBefore(
                LocalDate.of(dateOfBirth.year, 4, 20)
            )) -> "Aries"

            (dateOfBirth.isAfter(LocalDate.of(dateOfBirth.year, 4, 19)) && dateOfBirth.isBefore(
                LocalDate.of(dateOfBirth.year, 5, 21)
            )) -> "Taurus"

            (dateOfBirth.isAfter(LocalDate.of(dateOfBirth.year, 5, 20)) && dateOfBirth.isBefore(
                LocalDate.of(dateOfBirth.year, 6, 21)
            )) -> "Gemini"

            (dateOfBirth.isAfter(LocalDate.of(dateOfBirth.year, 6, 20)) && dateOfBirth.isBefore(
                LocalDate.of(dateOfBirth.year, 7, 23)
            )) -> "Cancer"

            (dateOfBirth.isAfter(LocalDate.of(dateOfBirth.year, 7, 22)) && dateOfBirth.isBefore(
                LocalDate.of(dateOfBirth.year, 8, 23)
            )) -> "Leo"

            (dateOfBirth.isAfter(LocalDate.of(dateOfBirth.year, 8, 22)) && dateOfBirth.isBefore(
                LocalDate.of(dateOfBirth.year, 9, 23)
            )) -> "Virgo"

            (dateOfBirth.isAfter(LocalDate.of(dateOfBirth.year, 9, 22)) && dateOfBirth.isBefore(
                LocalDate.of(dateOfBirth.year, 10, 23)
            )) -> "Libra"

            (dateOfBirth.isAfter(LocalDate.of(dateOfBirth.year, 10, 22)) && dateOfBirth.isBefore(
                LocalDate.of(dateOfBirth.year, 11, 22)
            )) -> "Scorpio"

            (dateOfBirth.isAfter(LocalDate.of(dateOfBirth.year, 11, 21)) && dateOfBirth.isBefore(
                LocalDate.of(dateOfBirth.year, 12, 22)
            )) -> "Sagittarius"

            (dateOfBirth.isAfter(LocalDate.of(dateOfBirth.year, 12, 21)) || dateOfBirth.isBefore(
                LocalDate.of(dateOfBirth.year, 1, 20)
            )) -> "Capricorn"

            (dateOfBirth.isAfter(LocalDate.of(dateOfBirth.year, 1, 19)) && dateOfBirth.isBefore(
                LocalDate.of(dateOfBirth.year, 2, 19)
            )) -> "Aquarius"

            (dateOfBirth.isAfter(LocalDate.of(dateOfBirth.year, 2, 18)) && dateOfBirth.isBefore(
                LocalDate.of(dateOfBirth.year, 3, 21)
            )) -> "Pisces"

            else -> "Unknown"
        }
    }
}