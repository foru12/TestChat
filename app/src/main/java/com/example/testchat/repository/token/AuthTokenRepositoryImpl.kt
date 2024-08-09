package com.example.testchat.repository.token

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.testchat.retrofit.model.ResponseRegister
import com.example.testchat.room.model.TokenData
import javax.inject.Inject


class AuthTokenRepositoryImpl @Inject constructor(
    context: Context
) : AuthTokenRepository {

    private val sharedPreferences = EncryptedSharedPreferences.create(
        "auth_prefs",
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override suspend fun saveTokenAuth(tokenData: TokenData) {
        sharedPreferences.edit().apply {
            putString("refresh_token", tokenData.refreshToken)
            putString("access_token", tokenData.accessToken)
            putInt("user_id", tokenData.userId)
            apply()
        }
    }




    override suspend fun getTokensAuth(): TokenData? {
        val refreshToken = sharedPreferences.getString("refresh_token", null)
        val accessToken = sharedPreferences.getString("access_token", null)
        val userId = sharedPreferences.getInt("user_id", -1)

        return if (refreshToken != null && accessToken != null && userId != -1) {
            TokenData(refreshToken, accessToken, userId)
        } else {
            null
        }
    }


}