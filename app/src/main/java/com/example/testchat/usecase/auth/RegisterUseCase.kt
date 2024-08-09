package com.example.testchat.usecase.auth

import com.example.testchat.repository.auth.RegisterRepository
import com.example.testchat.retrofit.model.RequestRegister
import com.example.testchat.retrofit.model.ResponseRegister
import retrofit2.Response
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val registerRepository: RegisterRepository
) {
    suspend operator fun invoke(phone: String, name: String, username: String): Response<ResponseRegister> {
        val request = RequestRegister(phone, name, username)
        return registerRepository.registerUser(request)
    }
}