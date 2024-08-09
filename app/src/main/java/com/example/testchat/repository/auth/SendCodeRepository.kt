package com.example.testchat.repository.auth

interface SendCodeRepository {
    suspend fun sendAuthCode(phone: String): Result<Boolean>
}