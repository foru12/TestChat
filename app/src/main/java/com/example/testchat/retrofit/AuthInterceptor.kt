package com.example.testchat.retrofit

import android.util.Log
import com.example.testchat.Logger
import com.example.testchat.repository.token.AuthTokenRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import javax.inject.Inject

/*
class AuthInterceptor @Inject constructor(
    private val authTokenRepository: AuthTokenRepository
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val originalRequest = chain.request()

        Logger.e("AuthInterceptor", "Attempting to retrieve token...")
        val tokenData = runBlocking { authTokenRepository.getTokensAuth() }
        val token = tokenData?.accessToken


        val requestWithToken = if (!token.isNullOrEmpty()) {
            Logger.e("AuthInterceptor", "Token retrieved: $token")
            originalRequest.newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
        } else {
            Logger.e("AuthInterceptor", "No token available.")
            originalRequest
        }
        Logger.e("AuthInterceptor", "Sending request to: ${requestWithToken.url}")
        return chain.proceed(requestWithToken)
    }
}*/


class AuthInterceptor @Inject constructor(
    private val authTokenRepository: AuthTokenRepository
) : Interceptor {


    //Список тех кому нужен access token

    private val excludedPaths = listOf(
        "/api/v1/users/check-jwt/",
        "/api/v1/users/refresh-token/",
        "/api/v1/users/me/"
    )

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request()
        val requestUrl = request.url.encodedPath


        Logger.e("AuthInterceptor", "Request URL: $requestUrl")
        Logger.e("AuthInterceptor", "Request Method: ${request.method}")


        val shouldAddToken = excludedPaths.any { requestUrl.contains(it) }

        if (shouldAddToken) {

            val tokenData = runBlocking { authTokenRepository.getTokensAuth() }


            if (tokenData != null) {
                Logger.e(
                    "AuthInterceptor",
                    "Token Data: AccessToken=${tokenData.accessToken}, RefreshToken=${tokenData.refreshToken}, UserId=${tokenData.userId}"
                )
            } else {
                Logger.e("AuthInterceptor", "No token data available")
            }


            val requestBuilder = request.newBuilder()
            if (tokenData != null) {
                val accessToken = tokenData.accessToken
                Logger.e("AuthInterceptor", "Adding Authorization header with token")
                requestBuilder.addHeader("Authorization", "Bearer $accessToken")
            }


            Logger.e("AuthInterceptor", "Final Request: ${requestBuilder.build()}")

            return chain.proceed(requestBuilder.build())
        } else {
            Logger.e("AuthInterceptor", "Skipping token addition for URL: $requestUrl")
            return chain.proceed(request)
        }
    }
}