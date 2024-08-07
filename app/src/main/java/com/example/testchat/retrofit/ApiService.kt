package com.example.testchat.retrofit

import com.example.testchat.retrofit.model.RepsonseProfileData
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/api/v1/users/check-jwt/")
    suspend fun checkToken(): Response<Unit>

    @GET("/api/v1/users/me/")
    suspend fun getProfileData(): Response<RepsonseProfileData>
}