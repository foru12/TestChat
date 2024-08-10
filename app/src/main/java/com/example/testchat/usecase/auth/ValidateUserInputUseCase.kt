package com.example.testchat.usecase.auth

import javax.inject.Inject

class ValidateUserInputUseCase  @Inject constructor(){

    fun validate(number: String, name: String, username: String): ValidationResult {
        val errors = mutableListOf<String>()

        if (number.length < 14) {
            errors.add("Invalid phone number. It should be 13 characters long.")
        }

        if (name.isEmpty()) {
            errors.add("Name cannot be empty.")
        }

        val usernamePattern = "^[a-zA-Z0-9_-]{3,}$"
        if (!username.matches(usernamePattern.toRegex())) {
            errors.add("Username must contain only letters, numbers, and -_ characters.")
        }

        return if (errors.isEmpty()) {
            ValidationResult.Success
        } else {
            ValidationResult.Error(errors)
        }
    }
}

sealed class ValidationResult {
    object Success : ValidationResult()
    data class Error(val errors: List<String>) : ValidationResult()
}