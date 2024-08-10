package com.example.testchat.retrofit

import android.util.Log
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

        Log.e("AuthInterceptor", "Attempting to retrieve token...")
        val tokenData = runBlocking { authTokenRepository.getTokensAuth() }
        val token = tokenData?.accessToken


        val requestWithToken = if (!token.isNullOrEmpty()) {
            Log.e("AuthInterceptor", "Token retrieved: $token")
            originalRequest.newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
        } else {
            Log.e("AuthInterceptor", "No token available.")
            originalRequest
        }
        Log.e("AuthInterceptor", "Sending request to: ${requestWithToken.url}")
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


        Log.e("AuthInterceptor", "Request URL: $requestUrl")
        Log.e("AuthInterceptor", "Request Method: ${request.method}")


        val shouldAddToken = excludedPaths.any { requestUrl.contains(it) }

        if (shouldAddToken) {

            val tokenData = runBlocking { authTokenRepository.getTokensAuth() }


            if (tokenData != null) {
                Log.e(
                    "AuthInterceptor",
                    "Token Data: AccessToken=${tokenData.accessToken}, RefreshToken=${tokenData.refreshToken}, UserId=${tokenData.userId}"
                )
            } else {
                Log.e("AuthInterceptor", "No token data available")
            }


            val requestBuilder = request.newBuilder()
            if (tokenData != null) {
                val accessToken = tokenData.accessToken
                Log.e("AuthInterceptor", "Adding Authorization header with token")
                requestBuilder.addHeader("Authorization", "Bearer $accessToken")
            }


            Log.e("AuthInterceptor", "Final Request: ${requestBuilder.build()}")

            return chain.proceed(requestBuilder.build())
        } else {
            Log.e("AuthInterceptor", "Skipping token addition for URL: $requestUrl")
            return chain.proceed(request)
        }
    }
}