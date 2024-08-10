package com.example.testchat.di

import com.example.testchat.repository.auth.PhoneNumberRepository
import com.example.testchat.repository.auth.RegisterRepository
import com.example.testchat.repository.auth.SendCodeRepository
import com.example.testchat.repository.chat.ChatRepository
import com.example.testchat.repository.profile.ProfileRepository
import com.example.testchat.repository.profile.api.EditProfileRepository
import com.example.testchat.repository.profile.room.ProfileRoomRepository
import com.example.testchat.repository.token.AuthTokenRepository
import com.example.testchat.repository.token.TokenRepository
import com.example.testchat.room.AppDatabase
import com.example.testchat.ui.splash.NetworkHelper
import com.example.testchat.usecase.auth.GetMaxPhoneNumberLengthUseCase
import com.example.testchat.usecase.auth.GetTokenUseCase
import com.example.testchat.usecase.auth.RegisterUseCase
import com.example.testchat.usecase.auth.SaveTokenUseCase
import com.example.testchat.usecase.auth.SendCodeUseCase
import com.example.testchat.usecase.auth.ValidateUserInputUseCase
import com.example.testchat.usecase.chats.GetChatsUseCase
import com.example.testchat.usecase.chats.GetMessagesUseCase
import com.example.testchat.usecase.chats.SendMessageUseCase
import com.example.testchat.usecase.profile.DeleteProfileDataUseCase
import com.example.testchat.usecase.profile.EditProfileUseCase
import com.example.testchat.usecase.profile.GetProfileDataByIdUseCase
import com.example.testchat.usecase.profile.InsertProfileDataUseCase
import com.example.testchat.usecase.profile.UpdateProfileDataUseCase
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
    fun provideUpdateProfileDataUseCase(repository: ProfileRoomRepository): UpdateProfileDataUseCase {
        return UpdateProfileDataUseCase(repository)
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
    fun provideLoadProfileDataUseCase(profileRepository: ProfileRepository): LoadProfileDataUseCase {
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


    @Provides
    @Singleton
    fun provideGetMaxPhoneNumberLengthUseCase(
        repository: PhoneNumberRepository
    ): GetMaxPhoneNumberLengthUseCase {
        return GetMaxPhoneNumberLengthUseCase(repository)
    }


    @Provides
    @Singleton
    fun provideRegisterUseCase(registerRepository: RegisterRepository): RegisterUseCase {
        return RegisterUseCase(registerRepository)
    }


    @Provides
    fun provideSaveTokensUseCase(authRepository: AuthTokenRepository): SaveTokenUseCase {
        return SaveTokenUseCase(authRepository)
    }

    @Provides
    fun provideGetTokensUseCase(authRepository: AuthTokenRepository): GetTokenUseCase {
        return GetTokenUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideSendAuthCodeUseCase(authRepository: SendCodeRepository): SendCodeUseCase {
        return SendCodeUseCase(authRepository)
    }


    @Provides
    @Singleton
    fun provideEditProfileUseCase(editProfileRepository: EditProfileRepository): EditProfileUseCase {
        return EditProfileUseCase(editProfileRepository)
    }

    @Provides
    fun provideValidateUserInputUseCase(): ValidateUserInputUseCase {
        return ValidateUserInputUseCase()
    }


    @Provides
    fun provideGetChatsUseCase(repository: ChatRepository): GetChatsUseCase {
        return GetChatsUseCase(repository)
    }

    @Provides
    fun provideGetMessagesUseCase(repository: ChatRepository): GetMessagesUseCase {
        return GetMessagesUseCase(repository)
    }

    @Provides
    fun provideSendMessageUseCase(repository: ChatRepository): SendMessageUseCase {
        return SendMessageUseCase(repository)
    }
}