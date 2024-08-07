package com.example.testchat.usecase.splash

import com.example.testchat.repository.token.TokenRepository
import javax.inject.Inject

class CheckTokenUseCase @Inject constructor(
    private val tokenRepository: TokenRepository
) {
    suspend fun execute(): Boolean {
        return tokenRepository.checkToken()
    }
}