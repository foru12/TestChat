package com.example.testchat.ui.splash

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

class NetworkHelper @Inject constructor(private val context: Context) {

    fun isNetworkConnected(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}