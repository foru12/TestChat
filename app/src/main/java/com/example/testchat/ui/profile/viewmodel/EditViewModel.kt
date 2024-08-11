package com.example.testchat.ui.profile.viewmodel

import android.content.ContentResolver
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testchat.Logger
import com.example.testchat.retrofit.model.RequestProfileData
import com.example.testchat.room.model.ProfileRoomData
import com.example.testchat.usecase.auth.GetTokenUseCase
import com.example.testchat.usecase.profile.EditProfileUseCase
import com.example.testchat.usecase.profile.ImageProcessorUseCase
import com.example.testchat.usecase.profile.InsertProfileDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EditViewModel
@Inject constructor(
    private val editProfileUseCase: EditProfileUseCase,
    private val imageProcessorUseCase: ImageProcessorUseCase
) : ViewModel() {
    private val _imageData = MutableLiveData<Pair<String, String>>()
    val imageData: LiveData<Pair<String, String>> = _imageData

    private val _editProfileState = MutableLiveData<EditProfileState>()
    val editProfileState: LiveData<EditProfileState> = _editProfileState

    fun editProfile(request: RequestProfileData) {
        _editProfileState.value = EditProfileState.Loading

        viewModelScope.launch {
            try {
                val response = editProfileUseCase.execute(request)
                if (response.isSuccessful) {
                    _editProfileState.value = EditProfileState.Success(request)
                } else {
                    Logger.e("Edit Error","Error: ${response.message()}")
                    _editProfileState.value = EditProfileState.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                Logger.e("Edit Exception","Exception: ${e.message}")
                _editProfileState.value = EditProfileState.Error("Exception: ${e.message}")
            }
        }
    }
    fun processImage(uri: Uri, contentResolver: ContentResolver) {
        viewModelScope.launch {
            val (fileName, base64Image) = imageProcessorUseCase.processImage(contentResolver, uri)

            var safeFileName = fileName ?: ""
            var safeBase64Image = base64Image ?: ""


            _imageData.value = Pair(safeFileName, safeBase64Image)
        }
    }

    fun processBitmap(bitmap: Bitmap) {
        viewModelScope.launch {
            val base64Image = imageProcessorUseCase.bitmapToBase64(bitmap)

            val safeFileName = "camera_image.jpg"
            val safeBase64Image = base64Image ?: ""

            _imageData.value = Pair(safeFileName, safeBase64Image)
        }
    }





}