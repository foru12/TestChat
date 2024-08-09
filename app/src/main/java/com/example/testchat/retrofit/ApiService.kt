package com.example.testchat.retrofit

import com.example.testchat.retrofit.model.RepsonseProfileData
import com.example.testchat.retrofit.model.RequestCheckCode
import com.example.testchat.retrofit.model.RequestRegister
import com.example.testchat.retrofit.model.RequestSendAuthCode
import com.example.testchat.retrofit.model.ResponseCheckCode
import com.example.testchat.retrofit.model.ResponseRegister
import com.example.testchat.retrofit.model.ResponseSendAuthCode
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("api/v1/users/check-jwt/")
    suspend fun checkToken(): Response<Unit>

    @GET("api/v1/users/me/")
    suspend fun getProfileData(): Response<RepsonseProfileData>


    @POST("api/v1/users/register/")
    suspend fun registerUser(@Body request: RequestRegister): Response<ResponseRegister>


    @POST("api/v1/users/send-auth-code/")
    suspend fun sendAuthCode(@Body request: RequestSendAuthCode): Response<ResponseSendAuthCode>


    @POST("api/v1/users/check-auth-code/")
    suspend fun checkAuthCode(@Body request: RequestCheckCode): Response<ResponseCheckCode>


}