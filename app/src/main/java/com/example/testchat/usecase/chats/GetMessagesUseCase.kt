package com.example.testchat.usecase.chats

import com.example.testchat.repository.chat.ChatRepository
import com.example.testchat.retrofit.model.Message
import javax.inject.Inject

class GetMessagesUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    suspend fun execute(chatId: String): List<Message> {
        return repository.getMessages(chatId)
    }
}