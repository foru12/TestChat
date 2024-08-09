package com.example.testchat.repository.auth

interface PhoneNumberRepository {
    fun getMaxPhoneNumberLength(countryCode: String): Int
}