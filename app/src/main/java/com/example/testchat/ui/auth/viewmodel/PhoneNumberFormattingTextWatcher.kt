package com.example.testchat.ui.auth.viewmodel

import android.text.Editable
import android.text.TextWatcher
import com.hbb20.CountryCodePicker

class PhoneNumberFormattingTextWatcher(
    private val ccp: CountryCodePicker,
    private val phoneNumberLengthProvider: PhoneNumberLengthProvider
) : TextWatcher {

    private var isFormatting: Boolean = false

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable?) {
        if (isFormatting || s.isNullOrEmpty()) return

        val countryCode = ccp.selectedCountryNameCode
        val maxLength = phoneNumberLengthProvider.getMaxPhoneNumberLength(countryCode)

        val digitsOnly = s.toString().replace(" ", "")

        if (digitsOnly.length > maxLength) {
            s.delete(maxLength, digitsOnly.length)
            return
        }

        isFormatting = true

        val formatted = StringBuilder()
        for (i in digitsOnly.indices) {
            if (i == 3 || i == 6 || i == 8) {
                formatted.append(" ")
            }
            formatted.append(digitsOnly[i])
        }

        s.replace(0, s.length, formatted.toString())
        isFormatting = false
    }
}