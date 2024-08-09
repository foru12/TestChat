package com.example.testchat.usecase.auth

import com.example.testchat.repository.auth.CheckCodeRepository
import com.example.testchat.retrofit.model.RequestCheckCode
import com.example.testchat.retrofit.model.ResponseCheckCode
import retrofit2.Response
import javax.inject.Inject

class CheckCodeUseCase @Inject constructor(private val checkCodeRepository: CheckCodeRepository) {
    suspend operator fun invoke(phone: String, code: String): Response<ResponseCheckCode> {
        return checkCodeRepository.checkAuthCode(phone, code)
    }
}