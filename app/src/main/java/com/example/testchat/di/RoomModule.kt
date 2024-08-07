package com.example.testchat.di

import android.content.Context
import androidx.room.Room
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
object RoomModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun provideProfileDataDao(db: AppDatabase): ProfileDataDao {
        return db.profileDataDao()
    }


}