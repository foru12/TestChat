package com.example.testchat.ui.auth.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.testchat.Logger
import com.example.testchat.retrofit.model.ResponseCheckCode
import com.example.testchat.room.model.TokenData
import com.example.testchat.usecase.auth.CheckCodeUseCase
import com.example.testchat.usecase.auth.GetTokenUseCase
import com.example.testchat.usecase.auth.SaveTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CodeViewModel @Inject constructor(
    private val checkCodeUseCase: CheckCodeUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val getTokenUseCase: GetTokenUseCase
) : ViewModel() {


    private val _authResult = MutableLiveData<Boolean>()
    val authResult: LiveData<Boolean> get() = _authResult

    fun checkAuthCode(phone: String, code: String) {
        viewModelScope.launch {
            try {
                Logger.e("Send Code", "$phone $code")
                val response = checkCodeUseCase(phone, code)

                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        saveUserData(responseBody)
                        _authResult.value = true
                    } else {
                        //todo обработать ошибку
                        _authResult.value = false
                    }
                } else {
                    _authResult.value = false
                }

                Logger.e("Response", response.toString())
            } catch (e: Exception) {
                val errorMessage = parseError(e.message)
                Logger.e("Error Message", errorMessage)
                _authResult.value = false
            }
        }
    }

    fun getUserData(): LiveData<TokenData?> = liveData {
        val responseRegister = getTokenUseCase()
        emit(responseRegister)
    }

    private fun saveUserData(responseCheckCode: ResponseCheckCode) {
        viewModelScope.launch {
            Log.d("Data is saved", responseCheckCode.toString())
            val tokenData = TokenData(
                responseCheckCode.refresh_token,
                responseCheckCode.access_token,
                responseCheckCode.user_id
            )
            saveTokenUseCase(tokenData)
        }
    }

    private fun parseError(message: String?): String {
        return message ?: "Unknown error"
    }
}
