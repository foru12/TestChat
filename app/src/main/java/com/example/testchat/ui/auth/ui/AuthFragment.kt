package com.example.testchat.ui.auth.ui

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.testchat.R
import com.example.testchat.databinding.FragmentAuthBinding
import com.example.testchat.databinding.FragmentCodeBinding

class AuthFragment : Fragment() {
    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        choiceAuth()


    }

    private fun choiceAuth() {

        binding.run {
            btnLogin.setOnClickListener {
                findNavController().navigate(R.id.action_authFragment_to_loginFragment)
            }
            btnSignIn.setOnClickListener {
                findNavController().navigate(R.id.action_authFragment_to_signinFragment)

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }
}