package com.example.testchat.ui.auth.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.testchat.R
import com.example.testchat.databinding.FragmentSigninBinding
import com.example.testchat.ui.auth.viewmodel.PhoneNumberFormattingTextWatcher
import com.example.testchat.ui.auth.viewmodel.SigninViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale


@AndroidEntryPoint
class SigninFragment : Fragment() {
    private var _binding: FragmentSigninBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SigninViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setClick()
        formatNumber()
    }

    private fun observeViewModel() {

        viewModel.registrationState.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                val navOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.authFragment, true)
                    .build()
                findNavController().navigate(R.id.action_signinFragment_to_chatsFragment, null, navOptions)
            }
        }

        viewModel.registrationError.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                displayError(it)
                Log.e("Signin Error", it)
            }
        }

        viewModel.validationErrors.observe(viewLifecycleOwner) { errors ->
            displayError(errors.joinToString("\n"))
        }
    }

    private fun displayError(errorMessage: String) {
        binding.tvErrorMessage.text = errorMessage
        binding.tvErrorMessage.visibility = View.VISIBLE
    }

    private fun formatNumber() {
        val phoneNumberFormattingTextWatcher = PhoneNumberFormattingTextWatcher(
            ccp = binding.countryCode,
            phoneNumberLengthProvider = viewModel,
            edNumber = binding.edNumber
        )

        binding.edNumber.addTextChangedListener(phoneNumberFormattingTextWatcher)

        binding.countryCode.setDefaultCountryUsingNameCode(Locale.getDefault().country)
        binding.countryCode.resetToDefaultCountry()
    }

    private fun setClick() {
        binding.btnSignin.setOnClickListener {
            val name = binding.edName.text.toString()
            val username = binding.edUserName.text.toString()
            val number = binding.countryCode.selectedCountryCodeWithPlus + binding.edNumber.text.toString()

            viewModel.registerUser(number, name, username)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSigninBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}