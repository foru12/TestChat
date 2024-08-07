package com.example.testchat.repository.token

import com.example.testchat.retrofit.ApiService
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : TokenRepository {
    override suspend fun checkToken(): Boolean {
        return try {
            val response = apiService.checkToken()
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }
}