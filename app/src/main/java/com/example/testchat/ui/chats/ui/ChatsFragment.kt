package com.example.testchat.ui.chats.ui

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.testchat.R
import com.example.testchat.databinding.FragmentChatsBinding
import com.example.testchat.databinding.FragmentLoginBinding
import com.example.testchat.ui.chats.ChatsAdapter
import com.example.testchat.ui.chats.viewmodel.ChatsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatsFragment : Fragment() {

    private var _binding: FragmentChatsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ChatsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.chats.observe(viewLifecycleOwner) { chats ->
            val adapter = ChatsAdapter(chats) { chat ->
                findNavController().navigate(
                    R.id.action_chatsFragment_to_chatFragment,
                    bundleOf("chatId" to chat.id)
                )
            }
            binding.recyclerViewChats.adapter = adapter
        }

        binding.imProfile.setOnClickListener {
            findNavController().navigate(R.id.action_chatsFragment_to_profileFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().finish()
        }
        _binding = FragmentChatsBinding.inflate(inflater, container, false)
        return binding.root
    }
}