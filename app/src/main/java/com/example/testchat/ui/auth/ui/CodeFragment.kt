package com.example.testchat.ui.auth.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.testchat.R
import com.example.testchat.databinding.FragmentCodeBinding
import com.example.testchat.ui.auth.viewmodel.CodeViewModel
import com.fraggjkee.smsconfirmationview.SmsConfirmationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CodeFragment : Fragment() {


    private var _binding: FragmentCodeBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val ARG_CONFIRMED_NUMBER = "confirmed_number"
    }

    private val viewModel: CodeViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val confirmedNumber = arguments?.getString(ARG_CONFIRMED_NUMBER)
        Log.e("Number", confirmedNumber.toString())


        viewModel.authResult.observe(viewLifecycleOwner) { result ->
            if (result) {
                val navOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.authFragment, true)
                    .build()
                findNavController().navigate(R.id.action_codeFragment_to_chatsFragment, null, navOptions)

            } else {
                Toast.makeText(
                    requireContext(),
                    requireContext().resources.getString(R.string.str_error_auth_code),
                    Toast.LENGTH_SHORT
                ).show()
            }

        }


        binding.smsCodeView.onChangeListener =
            SmsConfirmationView.OnChangeListener { code, isComplete ->
                Log.e("Code", code.toString())
                Log.e("Code", isComplete.toString())
                if (isComplete) {
                    viewModel.checkAuthCode(confirmedNumber.toString(), code)
                }
            }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCodeBinding.inflate(inflater, container, false)
        return binding.root
    }
}