package com.example.testchat.repository.token

interface TokenRepository {
    suspend fun checkToken(): Boolean
}