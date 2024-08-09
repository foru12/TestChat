package com.example.testchat.repository.auth

import com.example.testchat.retrofit.ApiService
import com.example.testchat.retrofit.ErrorParser.parseError
import com.example.testchat.retrofit.model.RequestCheckCode
import com.example.testchat.retrofit.model.ResponseCheckCode
import retrofit2.Response
import javax.inject.Inject

class CheckCodeRepositoryImpl@Inject constructor(
    private val apiService: ApiService
) : CheckCodeRepository {
    override suspend fun checkAuthCode(phone: String, code: String): Response<ResponseCheckCode> {
        return try {
            val response = apiService.checkAuthCode(RequestCheckCode(phone, code))
            if (response.isSuccessful){
                response
            }else{
                val errorBody = response.errorBody()?.string()
                val errorMessage = parseError(errorBody)
                Response.error(response.code(), okhttp3.ResponseBody.create(null, errorMessage.toString()))
            }
        } catch (e: Exception) {
            Response.error(500, okhttp3.ResponseBody.create(null, "Registration failed: ${e.message}"))
        }
    }
}