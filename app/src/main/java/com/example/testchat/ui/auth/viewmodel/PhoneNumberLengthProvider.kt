package com.example.testchat.ui.auth.viewmodel

interface PhoneNumberLengthProvider {
    fun getMaxPhoneNumberLength(countryCode: String): Int
}