package com.example.testchat.di

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
}