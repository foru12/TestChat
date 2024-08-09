package com.example.testchat.ui.auth.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.testchat.R
import com.example.testchat.databinding.FragmentSigninBinding
import com.example.testchat.retrofit.model.ResponseRegister
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
        viewModel.registrationState.observe(viewLifecycleOwner) { responseRegister ->
            responseRegister?.let {
                //todo сделать так что бы обратно не переходил
                findNavController().navigate(R.id.action_signinFragment_to_chatsFragment)

                Log.e("Signin Success", "Received data: $it")

            } ?: run {
                Log.e("Signin Error", "No data available")
            }
        }

        viewModel.registrationError.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                //todo сделать обработку ошибки
                Log.e("Signin Error", it)
            }
        }
    }



    private fun formatNumber() {


        val phoneNumberFormattingTextWatcher = PhoneNumberFormattingTextWatcher(
            ccp = binding.countryCode,
            phoneNumberLengthProvider = viewModel
        )

        binding.edNumber.addTextChangedListener(phoneNumberFormattingTextWatcher)

        binding.countryCode.setDefaultCountryUsingNameCode(Locale.getDefault().country)
        binding.countryCode.resetToDefaultCountry()
    }

    private fun setClick() {
        binding.btnSignin.setOnClickListener {
            val name = binding.edName.text.toString()
            val username = binding.edUserName.text.toString()
            val number =binding.countryCode.selectedCountryCodeWithPlus + binding.edNumber.text.toString()




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
}