package com.example.testchat.ui.auth.ui

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.testchat.Logger
import com.example.testchat.R
import com.example.testchat.databinding.FragmentLoginBinding
import com.example.testchat.ui.auth.viewmodel.LoginViewModel
import com.example.testchat.ui.auth.viewmodel.PhoneNumberFormattingTextWatcher
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class LoginFragment : Fragment() {


    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!



    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setClick()
        formatNumber()

    }

    private fun validatePhoneNumber(phoneNumber: String): Boolean {
        return phoneNumber.length == 13
    }

    private fun formatNumber() {


        val phoneNumberWatcher = PhoneNumberFormattingTextWatcher(binding.countryCode, viewModel,binding.edNumber)
        binding.edNumber.addTextChangedListener(phoneNumberWatcher)

        binding.countryCode.setDefaultCountryUsingNameCode(Locale.getDefault().country)
        binding.countryCode.resetToDefaultCountry()


        binding.countryCode.setOnCountryChangeListener {
            binding.edNumber.text?.clear()
        }

    }

    private fun showKeyboard(editText: EditText) {
        editText.requestFocus()
        val imm = editText.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun setClick() {
        binding.run {
            btnSend.setOnClickListener {
                if (validatePhoneNumber(edNumber.text.toString()))
                showDialog(countryCode.selectedCountryCodeWithPlus + edNumber.text.toString())
                else{
                    edNumber.setBackgroundResource(R.drawable.ed_fail_bg)
                }
            }
        }
    }

    private fun showDialog(number: String) {
        val dialog = MyDialogFragment.newInstance(number)
        dialog.onConfirm = { confirmedNumber ->
            val bundle = Bundle().apply {
                putString(CodeFragment.ARG_CONFIRMED_NUMBER, confirmedNumber)
            }

            viewModel.authResult.observe(viewLifecycleOwner) { response ->
                if (response.isSuccess){
                    findNavController().navigate(R.id.action_loginFragment_to_codeFragment, bundle)

                }else{
                    //todo обработать ошибку
                }
            }
            viewModel.sendAuthCode(confirmedNumber)






        }
        dialog.onChange = {
            showKeyboard(binding.edNumber)
        }
        dialog.show(parentFragmentManager, "MyDialogFragment")

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}