package com.example.testchat.repository.auth

import com.example.testchat.retrofit.model.ResponseCheckCode
import retrofit2.Response

interface CheckCodeRepository {
    suspend fun checkAuthCode(phone: String, code: String): Response<ResponseCheckCode>
}