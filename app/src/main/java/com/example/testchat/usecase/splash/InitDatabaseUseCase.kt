package com.example.testchat.usecase.splash

import com.example.testchat.room.AppDatabase
import javax.inject.Inject

class InitDatabaseUseCase @Inject constructor(
    private val database: AppDatabase
) {
    suspend fun execute() {
        // инициализация базы данных
    }
}