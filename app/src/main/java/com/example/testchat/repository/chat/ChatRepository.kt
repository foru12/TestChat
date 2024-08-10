package com.example.testchat.repository.chat

import com.example.testchat.retrofit.model.Chat
import com.example.testchat.retrofit.model.Message

interface ChatRepository {

    suspend fun getChats(): List<Chat>
    suspend fun getMessages(chatId: String): List<Message>
    suspend fun sendMessage(chatId: String, message: Message)

}