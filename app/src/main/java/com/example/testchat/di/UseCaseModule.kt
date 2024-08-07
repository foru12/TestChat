package com.example.testchat.di

import com.example.testchat.repository.chat.ChatRepository
import com.example.testchat.repository.profile.api.ProfileApiRepository
import com.example.testchat.repository.profile.room.ProfileRoomRepository
import com.example.testchat.repository.token.TokenRepository
import com.example.testchat.room.AppDatabase
import com.example.testchat.ui.splash.NetworkHelper
import com.example.testchat.usecase.profile.DeleteProfileDataUseCase
import com.example.testchat.usecase.profile.GetProfileDataByIdUseCase
import com.example.testchat.usecase.profile.InsertProfileDataUseCase
import com.example.testchat.usecase.splash.CheckAppVersionUseCase
import com.example.testchat.usecase.splash.CheckNetworkUseCase
import com.example.testchat.usecase.splash.CheckTokenUseCase
import com.example.testchat.usecase.splash.InitDatabaseUseCase
import com.example.testchat.usecase.splash.LoadChatsUseCase
import com.example.testchat.usecase.splash.LoadProfileDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetProfileDataByIdUseCase(repository: ProfileRoomRepository): GetProfileDataByIdUseCase {
        return GetProfileDataByIdUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideInsertProfileDataUseCase(repository: ProfileRoomRepository): InsertProfileDataUseCase {
        return InsertProfileDataUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteProfileDataUseCase(repository: ProfileRoomRepository): DeleteProfileDataUseCase {
        return DeleteProfileDataUseCase(repository)
    }


    @Provides
    @Singleton
    fun provideCheckTokenUseCase(
        tokenRepository: TokenRepository
    ): CheckTokenUseCase {
        return CheckTokenUseCase(tokenRepository)
    }

    @Provides
    @Singleton
    fun provideInitDatabaseUseCase(
        database: AppDatabase
    ): InitDatabaseUseCase {
        return InitDatabaseUseCase(database)
    }

    @Provides
    @Singleton
    fun provideLoadProfileDataUseCase(
        profileRepository: ProfileApiRepository
    ): LoadProfileDataUseCase {
        return LoadProfileDataUseCase(profileRepository)
    }

    @Provides
    @Singleton
    fun provideLoadChatsUseCase(
        chatRepository: ChatRepository
    ): LoadChatsUseCase {
        return LoadChatsUseCase(chatRepository)
    }

    @Provides
    @Singleton
    fun provideCheckAppVersionUseCase(): CheckAppVersionUseCase {
        return CheckAppVersionUseCase()
    }

    @Provides
    @Singleton
    fun provideCheckNetworkUseCase(
        networkHelper: NetworkHelper
    ): CheckNetworkUseCase {
        return CheckNetworkUseCase(networkHelper)
    }


}