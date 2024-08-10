package com.example.testchat.ui.chats.ui

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testchat.R
import com.example.testchat.databinding.FragmentChatBinding
import com.example.testchat.databinding.FragmentChatsBinding
import com.example.testchat.ui.chats.MessagesAdapter
import com.example.testchat.ui.chats.viewmodel.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ChatViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chatId = arguments?.getString("chatId") ?: return


        viewModel.loadMessages(chatId)

        val adapter = MessagesAdapter(emptyList())
        binding.recyclerViewMessages.adapter = adapter

        viewModel.messages.observe(viewLifecycleOwner) { messages ->
            adapter.updateMessages(messages)
            binding.recyclerViewMessages.scrollToPosition(messages.size - 1)
        }


        binding.btnSend.setOnClickListener {
            val messageText = binding.edMessage.text.toString()
            if (messageText.isNotEmpty()) {
                viewModel.sendMessage(chatId, messageText)
                binding.edMessage.text.clear()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }
}