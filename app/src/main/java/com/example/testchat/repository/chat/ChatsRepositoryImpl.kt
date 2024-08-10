package com.example.testchat.repository.chat

import android.util.Log
import com.example.testchat.retrofit.model.Chat
import com.example.testchat.retrofit.model.Message
import javax.inject.Inject

class ChatsRepositoryImpl @Inject constructor() : ChatRepository {

    private val chats = listOf(
        Chat("1", "Chat 1"),
        Chat("2", "Chat 2"),
        Chat("3", "Chat 3")
    )

    private val messagesMap = mutableMapOf(
        "1" to mutableListOf(
            Message("1", "Hello!"),
            Message("2", "How are you?")

        ),
        "2" to mutableListOf(
            Message("1", "Hi!"),
            Message("2", "What's up?")
        ),
        "3" to mutableListOf(
            Message("1", "Hey!"),
            Message("2", "Good morning!")
        )
    )

    override suspend fun getChats(): List<Chat> = chats

    override suspend fun sendMessage(chatId: String, message: Message) {
        val chatMessages = messagesMap[chatId]?.toMutableList() ?: mutableListOf()
        chatMessages.add(message)
        messagesMap[chatId] = chatMessages
        Log.d("ChatsRepositoryImpl", "Message added to chat $chatId: ${messagesMap[chatId]}")
    }
    override suspend fun getMessages(chatId: String): List<Message> {
        val messages = messagesMap[chatId] ?: emptyList()
        Log.d("ChatsRepositoryImpl", "Returning messages for chatId $chatId: $messages")
        return messages
    }
}