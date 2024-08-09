package com.example.testchat.repository.auth

import com.example.testchat.retrofit.model.RequestRegister
import com.example.testchat.retrofit.model.ResponseRegister
import retrofit2.Response

interface RegisterRepository {
    suspend fun registerUser(request: RequestRegister): Response<ResponseRegister>
}