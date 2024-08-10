package com.example.testchat.ui.chats.ui

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.testchat.R
import com.example.testchat.databinding.FragmentChatsBinding
import com.example.testchat.databinding.FragmentLoginBinding
import com.example.testchat.ui.chats.viewmodel.ChatsViewModel

class ChatsFragment : Fragment() {



    private var _binding: FragmentChatsBinding? = null
    private val binding get() = _binding!!


    companion object {
        fun newInstance() = ChatsFragment()
    }

    private val viewModel: ChatsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.imProfile.setOnClickListener{
            findNavController().navigate(R.id.action_chatsFragment_to_profileFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatsBinding.inflate(inflater, container, false)
        return binding.root
    }
}