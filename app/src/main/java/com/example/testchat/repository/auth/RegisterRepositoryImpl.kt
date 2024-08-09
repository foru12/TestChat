package com.example.testchat.repository.auth

import com.example.testchat.retrofit.ApiService
import com.example.testchat.retrofit.ErrorParser.parseError
import com.example.testchat.retrofit.model.RequestRegister
import com.example.testchat.retrofit.model.ResponseError
import com.example.testchat.retrofit.model.ResponseRegister
import kotlinx.serialization.json.Json
import retrofit2.Response
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : RegisterRepository {

    override suspend fun registerUser(request: RequestRegister): Response<ResponseRegister> {
        return try {
            val response = apiService.registerUser(request)

            if (response.isSuccessful) {
                response
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = parseError(errorBody)
                Response.error(response.code(), okhttp3.ResponseBody.create(null, errorMessage.toString()))
            }
        } catch (e: Exception) {
            Response.error(500, okhttp3.ResponseBody.create(null, "Registration failed: ${e.message}"))
        }
    }


}
