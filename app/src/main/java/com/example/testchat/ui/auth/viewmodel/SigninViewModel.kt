package com.example.testchat.ui.auth.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.testchat.retrofit.model.ResponseRegister
import com.example.testchat.room.model.TokenData
import com.example.testchat.usecase.auth.GetMaxPhoneNumberLengthUseCase
import com.example.testchat.usecase.auth.GetTokenUseCase
import com.example.testchat.usecase.auth.RegisterUseCase
import com.example.testchat.usecase.auth.SaveTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject


@HiltViewModel
class SigninViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val getMaxPhoneNumberLengthUseCase: GetMaxPhoneNumberLengthUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val getTokenUseCase: GetTokenUseCase
) : ViewModel(), PhoneNumberLengthProvider {

    private val _registrationState = MutableLiveData<Boolean>()
    val registrationState: LiveData<Boolean> get() = _registrationState


    private val _registrationError = MutableLiveData<String?>()
    val registrationError: LiveData<String?> = _registrationError

    fun registerUser(phone: String, name: String, username: String) {
        viewModelScope.launch {
            try {
                Log.e("Send Register", "${removeSpaces(phone)}, $name, $username")
                val response = registerUseCase(removeSpaces(phone), name, username)

                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        saveUserData(responseBody)
                        _registrationState.value = true
                        _registrationError.value = null
                    } else {
                        Log.e("Response Data", "Null")
                        _registrationState.value = false
                        _registrationError.value = "No data available"
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = parseError(errorBody)
                    Log.e("Message Error", errorMessage)
                    _registrationState.value = false
                    _registrationError.value = errorMessage
                }
            } catch (e: HttpException) {
                _registrationState.value = false
                _registrationError.value = "HTTP error: ${e.message}"
                Log.e(" HttpExc Error", e.message.toString())
            } catch (e: Exception) {
                Log.e("Exc Error", e.message.toString())
                _registrationError.value = "Registration failed: ${e.message}"
                _registrationState.value = false
            }
        }
    }

    private fun saveUserData(responseRegister: ResponseRegister) {
        viewModelScope.launch {
            Log.e("Data is saved", responseRegister.toString())
            val tokenData = TokenData(
                responseRegister.refreshToken,
                responseRegister.accessToken,
                responseRegister.userId
            )
            saveTokenUseCase(tokenData)
        }
    }



    private fun removeSpaces(phone: String): String {
        return phone.replace(" ", "")
    }

    override fun getMaxPhoneNumberLength(countryCode: String): Int {
        return getMaxPhoneNumberLengthUseCase.execute(countryCode)
    }

    private fun parseError(errorBody: String?): String {
        return errorBody ?: "Unknown error"
    }
}