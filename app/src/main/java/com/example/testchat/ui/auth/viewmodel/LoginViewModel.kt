package com.example.testchat.ui.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testchat.usecase.auth.GetMaxPhoneNumberLengthUseCase
import com.example.testchat.usecase.auth.SendCodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getMaxPhoneNumberLengthUseCase: GetMaxPhoneNumberLengthUseCase,
    private val sendCodeUseCase: SendCodeUseCase
) : ViewModel(), PhoneNumberLengthProvider {


    private val _authResult = MutableLiveData<Result<Boolean>>()
    val authResult: LiveData<Result<Boolean>> = _authResult

    fun sendAuthCode(phone: String) {
        viewModelScope.launch {
            val result = sendCodeUseCase(phone)
            _authResult.postValue(result)
        }
    }

    override fun getMaxPhoneNumberLength(countryCode: String): Int {
        return getMaxPhoneNumberLengthUseCase.execute(countryCode)
    }





}