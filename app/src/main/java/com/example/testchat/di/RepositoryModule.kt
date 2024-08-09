package com.example.testchat.di

import android.content.Context
import com.example.testchat.repository.auth.CheckCodeRepository
import com.example.testchat.repository.auth.CheckCodeRepositoryImpl
import com.example.testchat.repository.token.AuthTokenRepository
import com.example.testchat.repository.token.AuthTokenRepositoryImpl
import com.example.testchat.repository.auth.PhoneNumberRepository
import com.example.testchat.repository.auth.PhoneNumberRepositoryImpl
import com.example.testchat.repository.auth.RegisterRepository
import com.example.testchat.repository.auth.RegisterRepositoryImpl
import com.example.testchat.repository.auth.SendCodeRepository
import com.example.testchat.repository.auth.SendCodeRepositoryImpl
import com.example.testchat.repository.chat.ChatRepository
import com.example.testchat.repository.chat.ChatRepositoryImpl
import com.example.testchat.repository.profile.api.ProfileApiRepository
import com.example.testchat.repository.profile.api.ProfileApiRepositoryImpl
import com.example.testchat.repository.profile.room.ProfileRoomRepository
import com.example.testchat.repository.profile.room.ProfileRoomRepositoryImpl
import com.example.testchat.repository.token.TokenRepository
import com.example.testchat.repository.token.TokenRepositoryImpl
import com.example.testchat.retrofit.ApiService
import com.example.testchat.room.AppDatabase
import com.example.testchat.room.ProfileDataDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideProfileRoomRepository(profileDao: ProfileDataDao): ProfileRoomRepository {
        return ProfileRoomRepositoryImpl(profileDao)
    }


    @Provides
    @Singleton
     fun provideProfileApiRepository(
        apiService: ApiService,
        database: AppDatabase
    ): ProfileApiRepository {
        return ProfileApiRepositoryImpl(apiService, database)
    }

    @Provides
    @Singleton
    fun provideTokenRepository(apiService: ApiService): TokenRepository {
        return TokenRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideChatRepository(apiService: ApiService): ChatRepository {
        return ChatRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun providePhoneNumberRepository(): PhoneNumberRepository {
        return PhoneNumberRepositoryImpl()
    }


    @Provides
    @Singleton
    fun provideUserRepository(apiService: ApiService): RegisterRepository {
        return RegisterRepositoryImpl(apiService)
    }


    @Provides
    @Singleton
    fun provideAuthTokenRepository(
        @ApplicationContext context: Context
    ): AuthTokenRepository {
        return AuthTokenRepositoryImpl(context)
    }


    @Provides
    @Singleton
    fun provideSendCodeRepository(
        apiService: ApiService
    ): SendCodeRepository {
        return SendCodeRepositoryImpl(apiService)
    }


    @Provides
    @Singleton
    fun provideCheckCodeRepository(
        apiService: ApiService
    ): CheckCodeRepository {
        return CheckCodeRepositoryImpl(apiService)
    }
}