package com.example.testchat.ui.splash.ui

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.testchat.R
import com.example.testchat.databinding.FragmentSplashBinding
import com.example.testchat.ui.splash.viewmodel.SplashViewModel
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {



    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = SplashFragment()
    }

    private val viewModel: SplashViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.networkError.observe(viewLifecycleOwner) { errorMessage ->
            if (errorMessage != null) {
                showNetworkError(errorMessage)
            }
        }


    }


    private fun showNetworkError(errorMessage: String) {
        binding.networkError.visibility = View.VISIBLE
        binding.btnTry.setOnClickListener {
            // Попробовать снова
            lifecycleScope.launch {
                viewModel.checkNetwork()
            }
        }
        //binding.errorMessage.text = errorMessage
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }
}