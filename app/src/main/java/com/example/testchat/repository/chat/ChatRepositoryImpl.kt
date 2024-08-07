package com.example.testchat.repository.chat

import com.example.testchat.retrofit.ApiService
import javax.inject.Inject

class ChatRepositoryImpl  @Inject constructor(
    private val apiService: ApiService
) : ChatRepository {
    override suspend fun getChats(): List<String>? {
        TODO("Not yet implemented")
    }

}