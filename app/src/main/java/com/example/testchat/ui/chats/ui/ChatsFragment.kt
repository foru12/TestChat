package com.example.testchat.ui.chats.ui

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testchat.R
import com.example.testchat.ui.chats.viewmodel.ChatsViewModel

class ChatsFragment : Fragment() {

    companion object {
        fun newInstance() = ChatsFragment()
    }

    private val viewModel: ChatsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_chats, container, false)
    }
}