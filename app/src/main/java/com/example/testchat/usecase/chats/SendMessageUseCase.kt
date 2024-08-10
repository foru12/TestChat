package com.example.testchat.usecase.chats

import com.example.testchat.repository.chat.ChatRepository
import com.example.testchat.retrofit.model.Message
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    suspend fun execute(chatId: String, message: Message) {
        repository.sendMessage(chatId, message)
    }
}