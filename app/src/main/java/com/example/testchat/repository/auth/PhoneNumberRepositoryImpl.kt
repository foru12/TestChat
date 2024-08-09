package com.example.testchat.repository.auth

import javax.inject.Inject

class PhoneNumberRepositoryImpl @Inject constructor() : PhoneNumberRepository {

    private val countryLengthMap = mapOf(
        "RU" to 10, // Россия
        "US" to 10, // США
        "IN" to 10, // Индия
        "BR" to 11  // Бразилия
    )

    override fun getMaxPhoneNumberLength(countryCode: String): Int {
        return countryLengthMap[countryCode] ?: 10
    }
}