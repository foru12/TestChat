package com.example.testchat.ui.splash.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.testchat.Logger
import com.example.testchat.R
import com.example.testchat.databinding.FragmentSplashBinding
import com.example.testchat.ui.splash.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : Fragment() {


    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!



    private val viewModel: SplashViewModel by viewModels()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.networkError.observe(viewLifecycleOwner) { errorMessage ->
            Logger.e("Network Status", "loading...")
            if (errorMessage != null) {
                Logger.e("Network Status", "Error")
                showNetworkError(errorMessage)
            } else {
                viewModel.checkToken()
                hideNetworkError()
            }
        }

        viewModel.checkNetwork()
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.splashFragment, true)
            .build()

        viewModel.tokenValid.observe(viewLifecycleOwner) { tokenValid ->
            if (tokenValid) {
                viewModel.loadProfileData()
                findNavController().navigate(R.id.action_splashFragment_to_chatsFragment, null, navOptions)

            } else {
                findNavController().navigate(R.id.action_splashFragment_to_authFragment, null, navOptions)
            }

        }
    }

    private fun hideNetworkError() {
        binding.run {
            networkError.visibility = View.GONE
            progressLoad.visibility = View.VISIBLE
        }


    }

    private fun showNetworkError(message: String) {

        binding.run {
            networkError.visibility = View.VISIBLE
            progressLoad.visibility = View.GONE
            txtError.text = message
            btnTry.text = requireContext().resources.getString(R.string.try_again)
            progressBtn.visibility = View.GONE
            btnTry.setOnClickListener {
                btnTry.text = ""
                progressBtn.visibility = View.VISIBLE
                lifecycleScope.launch {
                    delay(1500)
                    viewModel.checkNetwork()
                }
            }
            txtError.text = message
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }
}