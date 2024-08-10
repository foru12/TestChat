package com.example.testchat.ui.chats.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testchat.retrofit.model.Message
import com.example.testchat.usecase.chats.GetMessagesUseCase
import com.example.testchat.usecase.chats.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getMessagesUseCase: GetMessagesUseCase,
    private val sendMessageUseCase: SendMessageUseCase
) : ViewModel() {

    private val _messages = MutableLiveData<List<Message>>()
    val messages: LiveData<List<Message>> get() = _messages

    fun loadMessages(chatId: String) {
        viewModelScope.launch {
            val loadedMessages = getMessagesUseCase.execute(chatId)
            Log.e("ChatViewModel", "Loaded messages: $loadedMessages")
            _messages.value = loadedMessages
        }
    }
    fun sendMessage(chatId: String, text: String) {
        viewModelScope.launch {
            try {
                val id = System.currentTimeMillis() /100000
                val message = Message(id = id.toString(), text = text)
                sendMessageUseCase.execute(chatId, message)
                delay(2000)

                val updatedMessages = getMessagesUseCase.execute(chatId)
                Log.e("ChatViewModel", "Updated messages after sending: $updatedMessages")
                delay(2000)
                _messages.value = updatedMessages
            } catch (e: Exception) {
                Log.e("ChatViewModel", "Error in sendMessage: ${e.message}")
            }
        }
    }
}