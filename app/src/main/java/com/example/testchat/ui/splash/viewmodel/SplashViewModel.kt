package com.example.testchat.ui.splash.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testchat.Logger
import com.example.testchat.usecase.auth.ValidateUserInputUseCase
import com.example.testchat.usecase.splash.CheckAppVersionUseCase
import com.example.testchat.usecase.splash.CheckNetworkUseCase
import com.example.testchat.usecase.splash.CheckTokenUseCase
import com.example.testchat.usecase.splash.InitDatabaseUseCase
import com.example.testchat.usecase.splash.LoadChatsUseCase
import com.example.testchat.usecase.splash.LoadProfileDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkTokenUseCase: CheckTokenUseCase,
    private val loadProfileDataUseCase: LoadProfileDataUseCase,
    private val checkNetworkUseCase: CheckNetworkUseCase,

) : ViewModel() {

    private val _networkError = MutableLiveData<String>()
    val networkError: LiveData<String> = _networkError

    private val _tokenValid = MutableLiveData<Boolean>()
    val tokenValid: LiveData<Boolean> = _tokenValid



    //Проверка токена перед входом
    fun checkToken() {
        viewModelScope.launch {
            val isValid = checkTokenUseCase.execute()
            _tokenValid.value = isValid
            if (isValid) {
                loadProfileData()
            }
        }
    }




    fun loadProfileData() {
        viewModelScope.launch {
            val roomData = loadProfileDataUseCase.execute()
            Logger.e("Room Data",roomData.toString())

        }
    }

    //грузим чаты еще на Splash
   /* fun loadChats() {
        viewModelScope.launch {
            loadChatsUseCase.execute()
        }
    }*/

    //Можем проверять версию в сторах и если там более новая то предложить обновить версию
  /*  fun checkAppVersion() {
        viewModelScope.launch {
            checkAppVersionUseCase.execute()
        }
    }*/

    fun checkNetwork() {
        _networkError.value = if (!checkNetworkUseCase.execute()) {
            "No internet connection. Please retry."
        } else {
            Logger.e("Network Status", "Succes")
            null
        }
    }

}