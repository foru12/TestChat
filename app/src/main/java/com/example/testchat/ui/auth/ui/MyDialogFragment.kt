package com.example.testchat.ui.auth.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.testchat.R
import com.example.testchat.databinding.FragmentDialogBinding

class MyDialogFragment : DialogFragment() {


    private var _binding: FragmentDialogBinding? = null
    private val binding get() = _binding!!

    var onConfirm: ((String) -> Unit)? = null
    var onChange: (() -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val number = arguments?.getString(ARG_NUMBER) ?: ""
        binding.textNumber.text = number


        binding.textMessage.text = getString(R.string.str_number_correctly)


        binding.buttonConfirm.setOnClickListener {
            onConfirm?.invoke(number)
            dismiss()
        }
        binding.buttonChange.setOnClickListener {
            onChange?.invoke()
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_NUMBER = "number"

        fun newInstance(number: String): MyDialogFragment {
            val fragment = MyDialogFragment()
            val args = Bundle().apply {
                putString(ARG_NUMBER, number)
            }
            fragment.arguments = args
            return fragment
        }
    }
}