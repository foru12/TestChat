package com.example.testchat.repository.token

import android.util.Log
import com.example.testchat.Logger
import com.example.testchat.retrofit.ApiService
import com.example.testchat.retrofit.ErrorParser
import com.example.testchat.retrofit.model.RequestRefreshToken
import com.example.testchat.room.model.TokenData
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val authTokenRepository: AuthTokenRepository
) : TokenRepository {

    override suspend fun checkToken(): Boolean {
        val tokenData = authTokenRepository.getTokensAuth()

        return if (tokenData != null) {
            try {
                val response = apiService.checkToken()
                if (response.isSuccessful) {
                    Logger.e("TokenRepositoryImpl", "Token is valid")
                    true
                } else {
                    Logger.e("TokenRepositoryImpl", "Token is invalid, attempting refresh")
                    refreshAndSaveToken(tokenData.refreshToken)
                }
            } catch (e: Exception) {
                Logger.e("TokenRepositoryImpl", "Error checking token: ${e.message}")
                false
            }
        } else {
            Logger.e("TokenRepositoryImpl", "No token data available")
            false
        }
    }


    private suspend fun refreshAndSaveToken(refreshToken: String): Boolean {
        return try {
            val response = apiService.refreshToken(RequestRefreshToken(refreshToken))
            if (response.isSuccessful) {
                response.body()?.let { newTokenData ->
                    if (newTokenData.user_id != null && newTokenData.access_token.isNotEmpty()) {
                        val tokenData = TokenData(
                            newTokenData.refresh_token,
                            newTokenData.access_token,
                            newTokenData.user_id
                        )
                        authTokenRepository.saveTokenAuth(tokenData)
                        Logger.e("TokenRepositoryImpl", "Token refreshed and saved successfully")
                        true
                    } else {
                        Logger.e(
                            "TokenRepositoryImpl",
                            "Token refresh failed: Missing required fields"
                        )
                        false
                    }
                } ?: run {
                    Logger.e("TokenRepositoryImpl", "Token refresh failed: Empty response body")
                    false
                }
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = ErrorParser.parseError(errorBody)
                Logger.e("TokenRepositoryImpl", "Token refresh failed: $errorMessage")
                false
            }
        } catch (e: Exception) {
            Logger.e("TokenRepositoryImpl", "Error refreshing token: ${e.message}")
            false
        }
    }
}