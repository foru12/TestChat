package com.example.testchat.ui.profile.ui

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class DateInputTextWatcher(private val editText: EditText) : TextWatcher {
    private var isEditing = false

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable?) {
        if (isEditing) return

        isEditing = true
        val input = s.toString()

        val digitsOnly = input.replace("[^\\d]".toRegex(), "")

      
        val formatted = StringBuilder()
        when {
            digitsOnly.length <= 4 -> {
                formatted.append(digitsOnly)
            }
            digitsOnly.length <= 6 -> {
                formatted.append(digitsOnly.substring(0, 4)).append('-')
                formatted.append(digitsOnly.substring(4))
            }
            else -> {
                formatted.append(digitsOnly.substring(0, 4)).append('-')
                formatted.append(digitsOnly.substring(4, 6)).append('-')
                formatted.append(digitsOnly.substring(6))
            }
        }

        
        editText.setText(formatted.toString())
        editText.setSelection(formatted.length)

        isEditing = false
    }
}
