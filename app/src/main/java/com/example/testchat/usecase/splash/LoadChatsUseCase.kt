package com.example.testchat.usecase.splash

import com.example.testchat.repository.chat.ChatRepository
import javax.inject.Inject

class LoadChatsUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {
    suspend fun execute() {
        // todo загрузки чатов
    }
}