package com.example.testchat.usecase.auth

import com.example.testchat.repository.token.AuthTokenRepository
import com.example.testchat.retrofit.model.ResponseRegister
import com.example.testchat.room.model.TokenData
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
    private val authTokenRepository: AuthTokenRepository
) {
    suspend operator fun invoke(): TokenData? {
        return authTokenRepository.getTokensAuth()
    }
}