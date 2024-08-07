package com.example.testchat.ui.splash.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val initDatabaseUseCase: InitDatabaseUseCase,
    private val loadProfileDataUseCase: LoadProfileDataUseCase,
    private val loadChatsUseCase: LoadChatsUseCase,
    private val checkAppVersionUseCase: CheckAppVersionUseCase,
    private val checkNetworkUseCase: CheckNetworkUseCase
) : ViewModel() {

    private val _networkError = MutableLiveData<String>()
    val networkError: LiveData<String> = _networkError

    private val _tokenValid = MutableLiveData<Boolean>()
    val tokenValid: LiveData<Boolean> = _tokenValid

    fun checkToken() {
        viewModelScope.launch {
            _tokenValid.value = checkTokenUseCase.execute()
        }
    }

    fun initDataBase() {
        viewModelScope.launch {
            initDatabaseUseCase.execute()
        }
    }

    fun loadProfileData() {
        viewModelScope.launch {
            loadProfileDataUseCase.execute()
        }
    }

    fun loadChats() {
        viewModelScope.launch {
            loadChatsUseCase.execute()
        }
    }

    fun checkAppVersion() {
        viewModelScope.launch {
            checkAppVersionUseCase.execute()
        }
    }

    fun checkNetwork() {
        if (!checkNetworkUseCase.execute()) {
            _networkError.value = "No internet connection. Please retry."
        }
    }

}