package com.example.testchat.usecase.splash

import com.example.testchat.ui.splash.NetworkHelper
import javax.inject.Inject

class CheckNetworkUseCase @Inject constructor(
    private val networkHelper: NetworkHelper
) {
    fun execute(): Boolean {
        return networkHelper.isNetworkConnected()
    }
}