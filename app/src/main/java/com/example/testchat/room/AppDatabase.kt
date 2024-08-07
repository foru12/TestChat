package com.example.testchat.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.testchat.room.model.ProfileRoomData

@Database(entities = [ProfileRoomData::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun profileDataDao(): ProfileDataDao
}