package com.example.testchat.usecase.auth

import com.example.testchat.repository.auth.SendCodeRepository
import javax.inject.Inject

class SendCodeUseCase @Inject constructor(
    private val sendCodeRepository: SendCodeRepository
) {

    suspend operator fun invoke(phone: String): Result<Boolean> {
        return sendCodeRepository.sendAuthCode(phone)
    }
}