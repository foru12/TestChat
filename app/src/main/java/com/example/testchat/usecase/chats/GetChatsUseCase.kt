package com.example.testchat.usecase.chats

import com.example.testchat.repository.chat.ChatRepository
import com.example.testchat.retrofit.model.Chat
import javax.inject.Inject

class GetChatsUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    suspend fun execute(): List<Chat> {
        return repository.getChats()
    }
}