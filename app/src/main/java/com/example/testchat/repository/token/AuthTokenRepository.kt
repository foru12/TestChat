package com.example.testchat.repository.token

import com.example.testchat.retrofit.model.ResponseRegister
import com.example.testchat.room.model.TokenData

interface AuthTokenRepository {
    suspend fun saveTokenAuth(tokenData: TokenData)
    suspend fun getTokensAuth(): TokenData?
}