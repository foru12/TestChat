package com.example.testchat.repository.auth

import com.example.testchat.retrofit.ApiService
import com.example.testchat.retrofit.model.RequestSendAuthCode
import javax.inject.Inject

class SendCodeRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : SendCodeRepository {

    override suspend fun sendAuthCode(phone: String): Result<Boolean> {
        return try {
            val response = apiService.sendAuthCode(RequestSendAuthCode(phone))
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null && body.is_success) {
                    Result.success(true)
                } else {
                    Result.failure(Exception("Error: response not successful or body is null"))
                }
            } else {
                //todo обработать ошибку
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}