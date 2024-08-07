package com.example.testchat.repository.chat

interface ChatRepository {

    suspend fun getChats(): List<String>?

}