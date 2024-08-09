package com.example.testchat.usecase.auth

import com.example.testchat.repository.auth.PhoneNumberRepository
import javax.inject.Inject

class GetMaxPhoneNumberLengthUseCase @Inject constructor(
    private val repository: PhoneNumberRepository
) {

    fun execute(countryCode: String): Int {
        return repository.getMaxPhoneNumberLength(countryCode)
    }
}